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

-- User table creation
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    email VARCHAR(255) NOT NULL,
                                    first_name VARCHAR(255) NOT NULL,
                                    last_name VARCHAR(255) NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    phone BIGINT NOT NULL,
                                    photo VARCHAR(255),
                                    role_id BIGINT,
                                    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE SET NULL
);
