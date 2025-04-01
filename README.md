# Project Management System

The Project Management System is designed to improve communication and collaboration within development teams by providing a comprehensive suite of tools. One of the key features of the system is the **Help Package**, which allows team members to request help, share solutions, and improve teamwork during the software development lifecycle. This system streamlines workflows, enhances productivity, and fosters a collaborative environment.

## Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Design Patterns & Architectures](#design-patterns--architectures)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About the Project

This project aims to provide a modern and effective solution for managing software development projects. It enables teams to track tasks, collaborate efficiently, and manage their workflows effectively. The Help Package feature is a unique aspect of the system that encourages team collaboration and knowledge sharing.

## Features

- **Help Package**: A dedicated feature for team members to request and offer help within the system. This encourages collaboration and knowledge sharing among developers, improving the overall efficiency of the team.
- **Customizable Dashboard**: Each company can personalize their dashboard by adding labels, priorities, and other features specific to their needs. This customization allows teams to tailor the platform to their unique workflow and project requirements.
- **Role-based Access Control**: Enables different roles, such as Developer, Manager, and Admin, with tailored permissions to access and manage the help system and other parts of the platform.
- **Project and Task Management**: The system allows users to manage projects, assign tasks, and track progress through intuitive interfaces, providing a comprehensive solution for team management.
- **Advanced Reporting**: Customizable reports to track task progress, team performance, and help requests, providing key insights into project health and team dynamics.
- **Iyzico Payment Integration**: Iyzico's integration ensures a smooth, reliable, and secure payment experience for users.

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.3
- **React**: for frontend [GitHub Repository](https://github.com/Sivellexfc/project-management-system-frontend)
- **MySQL**: for database management
- **Docker**: for containerizing services
- **JWT & Spring Security**: for secure user authentication
- **Swagger**: for API documentation

## Design Patterns & Architectures

In this project, several software design patterns and principles are applied to ensure scalability, maintainability, and clean code.

- **SOLID Principles**: Used to ensure the code is clean, maintainable, and extensible.
- **Adapter Pattern**: Applied for integrating third-party services and providing flexibility.
- **Builder Pattern**: Used for constructing complex objects step by step.
- **Singleton Pattern**: Ensures a single instance of certain objects to manage shared resources.

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

2. Set up the backend:
   - Navigate to the backend directory and run the application using:
     ```bash
     ./mvnw spring-boot:run
     ```

3. Set up the frontend:
   - Navigate to the frontend directory and run the application using:
     ```bash
     npm install
     npm start
     ```

4. Set up the MySQL database and configure the `application.properties` with your database credentials.

5. Use Docker for containerization:
   - Build and run the Docker container for the backend and frontend services.

## Usage

Once the application is set up, you can access the Project Management System through your local environment. You will be able to manage projects, tasks, request help, and generate reports.

## Postman Documentation

You can view and test the API endpoints using the Postman collection [here](https://documenter.getpostman.com/view/24116867/2sAYdfoqUB).

## Contributing

Contributions to the project are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
