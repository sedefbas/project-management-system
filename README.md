# Project Managment System

[A short description of the project. Explain what the project does and its main purpose.]

## Table of Contents

- [About the Project](#about-the-project)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About the Project

[The Project Management System aims to enhance communication and collaboration within development teams by providing a Help Package feature. This feature allows team members to request help, share solutions, and improve teamwork during the software development lifecycle. ]

### Features

- **Help Package:** A dedicated feature for team members to request and offer help within the system. This encourages collaboration and knowledge sharing among developers, improving the overall efficiency of the team.
- **Customizable Dashboard:** Each company can personalize their dashboard by adding labels, priorities, and other features specific to their needs. This customization allows teams to tailor the platform to their unique workflow and project requirements.
- **Role-based Access Control:** Enables different roles, such as Developer, Manager, and Admin, with tailored permissions to access and manage the help system and other parts of the platform.
- **Project and Task Management:** The system allows users to manage projects, assign tasks, and track progress through intuitive interfaces, providing a comprehensive solution for team management.
- **Advanced Reporting:** Customizable reports to track task progress, team performance, and help requests, providing key insights into project health and team dynamics.
- **Iyzico Payment Integration:** Iyzico's integration ensures a smooth, reliable, and secure payment experience for users.

### Technologies Used

- **Java**: 21
- **Spring Boot**: 3.3
- **React**: for frontend [https://github.com/Sivellexfc/project-management-system-frontend]
- **MySQL**: 
- **Docker**:

### Design Patterns & Architectures

In this project, several software design patterns and principles are applied to ensure scalability, maintainability, and clean code.

- **SOLID Principles**:
- **Adapter Pattern**: 
- **Builder Pattern**:
- **Singleton Pattern**: 

## Installation

To set up this project locally, follow these steps:

### Prerequisites

- [Java](https://www.oracle.com/java/)
- [Docker](https://www.docker.com/)
- [MySQL](https://www.mysql.com/)
- [Git](https://git-scm.com/)

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/sedefbas/project-management-system.git
    ```

2. Navigate into the project directory:
    ```bash
    cd project-management-system
    ```

3. Install the dependencies:
    ```bash
    npm install  # For the frontend
    mvn install  # For the backend
    ```

4. Set up the MySQL database:
    - Create a new database in MySQL:
      ```sql
      CREATE DATABASE yonetim;
      ```
    - Update the `application.properties` (Spring Boot) or `config.json` (Node.js) file with your database credentials.

5. Start the application:
    - For the frontend:
        ```bash
        npm start
        ```
    - For the backend:
        ```bash
        mvn spring-boot:run
        ```

6. Your app should now be running! Access it at [http://localhost:8085](http://localhost:8085).

## Postman

[https://documenter.getpostman.com/view/24116867/2sAYdfoqUB]

