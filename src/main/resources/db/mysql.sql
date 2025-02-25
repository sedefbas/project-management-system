-- Role table creation
CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    description TEXT,
                                    status ENUM('ACTIVE', 'PASSIVE') NOT NULL
);

-- Permission table creation
CREATE TABLE IF NOT EXISTS permission (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
                                          description TEXT,
                                          is_hidden BOOLEAN NOT NULL,
                                          status ENUM('ACTIVE', 'PASSIVE') NOT NULL
);

-- Role-Permission relationship table creation
CREATE TABLE IF NOT EXISTS role_permission (
                                               role_id BIGINT NOT NULL,
                                               permission_id BIGINT NOT NULL,
                                               PRIMARY KEY (role_id, permission_id),
                                               FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
                                               FOREIGN KEY (permission_id) REFERENCES permission(id) ON DELETE CASCADE
);

-- User table creation with status column
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    email VARCHAR(255) NOT NULL,
                                    first_name VARCHAR(255) NOT NULL,
                                    last_name VARCHAR(255) NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    phone BIGINT NOT NULL,
                                    photo VARCHAR(255),
                                    role_id BIGINT,
                                    status ENUM('NOT_VERIFIED', 'VERIFIED', 'BLOCKED') NOT NULL DEFAULT 'NOT_VERIFIED',
                                    last_login_time TIMESTAMP,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE SET NULL
);

-- Subscription Plan table
CREATE TABLE IF NOT EXISTS subscription_plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('FREE', 'PREMIUM', 'ENTERPRISE') NOT NULL,
    description TEXT,
    max_proje INT,
    max_task INT,
    max_user INT,
    price DECIMAL(10,2),
    features TEXT
);

-- User Verification table
CREATE TABLE IF NOT EXISTS user_verification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'EXPIRED') NOT NULL,
    type ENUM('EMAIL', 'PHONE') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- Companies table
CREATE TABLE IF NOT EXISTS companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    phone_number BIGINT,
    email VARCHAR(255),
    website VARCHAR(255),
    status ENUM('ACTIVE', 'PASSIVE', 'SUSPENDED') NOT NULL,
    subscription_plan_id BIGINT,
    FOREIGN KEY (subscription_plan_id) REFERENCES subscription_plan(id)
);

-- Company Owners table
CREATE TABLE IF NOT EXISTS company_owners (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    UNIQUE KEY unique_company_user (company_id, user_id)
);

-- Company Users table
CREATE TABLE IF NOT EXISTS company_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- Projects table
CREATE TABLE IF NOT EXISTS projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    photo VARCHAR(255),
    status ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD', 'CANCELLED') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    company_id BIGINT NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);

-- Stages table
CREATE TABLE IF NOT EXISTS stages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    context TEXT,
    is_default BOOLEAN DEFAULT FALSE
);

-- Priorities table
CREATE TABLE IF NOT EXISTS priorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    photo VARCHAR(255),
    is_default BOOLEAN DEFAULT FALSE
);

-- Labels table
CREATE TABLE IF NOT EXISTS labels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    photo VARCHAR(255),
    is_default BOOLEAN DEFAULT FALSE
);

-- Bands (Groups) table
CREATE TABLE IF NOT EXISTS bands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    company_id BIGINT NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);

-- Sub Bands (SubGroups) table
CREATE TABLE IF NOT EXISTS sub_bands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    group_id BIGINT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES bands(id) ON DELETE CASCADE
);
