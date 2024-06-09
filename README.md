# Docker Exam API Documentation

## Overview

This API is designed to manage user data via CRUD operations using Spring Boot and Docker. It supports creating, retrieving, updating, and deleting user information in a MySQL database. The application is containerized with Docker to ensure consistent environments across development and production setups.

### Technologies Used
- **Spring Boot**: Backend framework.
- **Java**: Programming language.
- **Docker**: Containerization of the application.
- **MySQL**: Database for storing user data.
- **Maven**: Project management and build automation.

This documentation outlines the setup, API usage, and Docker container management.

## Prerequisites

Before you can use the API and run the Docker containers, ensure you have the following installed on your system:

- **Java JDK 17**: Required to run the Spring Boot application. [Download Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Maven**: Needed for project dependency management and to build the application. [Download Maven](https://maven.apache.org/download.cgi)
- **Docker**: Essential for creating and managing your application's containers. [Download Docker](https://www.docker.com/products/docker-desktop)
- **Docker Compose**: Used to define and run multi-container Docker applications. Typically included with Docker Desktop for Windows and Mac. [Docker Compose Documentation](https://docs.docker.com/compose/)

Ensure that all these tools are correctly installed and configured on your machine to proceed with setting up and running the application as described in the following sections.

## Setup and Running

### Docker Compose Setup for MySQL

This setup uses Docker Compose to manage the MySQL database container, ensuring a consistent configuration for development and production environments.

1. **Start MySQL Container**:
   - Ensure Docker is running on your machine.
   - Navigate to the root of the `docker_exam` project where the `docker-compose.yml` file is located:
     ```bash
     cd path/to/docker_exam
     ```
   - Run the following command to start the MySQL container in detached mode:
     ```bash
     docker-compose up -d db_mysql
     ```
   - This command allows the MySQL service to run in the background, freeing up your terminal for other tasks.

2. **Verify MySQL is Running**:
   - Check the status of your Docker containers to confirm that the `db_mysql` container is running:
     ```bash
     docker ps
     ```
   - Look for `db_mysql` in the list of active containers.

### Running the Spring Boot Application Locally

With the MySQL database running in Docker, you can now start the Spring Boot application:

1. **Build the Application**:
   - Compile the application using Maven. Navigate to the root of the project directory and run:
     ```bash
     mvn clean install
     ```

2. **Run the Spring Boot Application**:
   - Start the application with:
     ```bash
     mvn spring-boot:run
     ```
   - This command launches the Spring Boot server, which connects to the MySQL database running in the Docker container.

### Stopping the Services

- **Stopping MySQL Container**:
  - To stop the MySQL container without removing it, use:
    ```bash
    docker-compose stop db_mysql
    ```
  - This command halts the container's process but retains the container state, allowing you to restart it later with:
    ```bash
    docker-compose start db_mysql
    ```

- **Shutting Down Spring Boot**:
  - To stop the Spring Boot application, simply terminate the process in your command line interface with `CTRL+C`.

## API Endpoints

The following sections describe the available RESTful endpoints for interacting with the Docker Exam API. Each endpoint provides specific functionality related to user management.

### Retrieve All Users

- **URL**: `http://localhost:8080/users`
- **Method**: `GET`
- **Description**: Retrieves a list of all registered users in the database.
- **Success Response**:
  - **Code**: `200 OK`
  - **Content Example**:
    ```json
    {
      "timestamp": "2024-06-10T12:00:00",
      "status": 200,
      "message": "Users retrieved successfully",
      "data": [
        {
          "id": 1,
          "name": "Lara Craft",
          "email": "lara.craft@example.com"
        },
        {
          "id": 2,
          "name": "Terry Crews",
          "email": "terry.crews@example.com"
        }
      ]
    }
    ```

### Create a New User

- **URL**: `http://localhost:8080/users`
- **Method**: `POST`
- **Description**: Creates a new user in the database with the provided name and email.
- **Request Body Example**:
  ```json
  {
    "name": "Nina Simone",
    "email": "nina.simone@example.com"
  }
  ```
- **Success Response**:
  - **Code**: `201 Created`
  - **Content Example**:
    ```json
    {
      "timestamp": "2024-06-10T12:00:00",
      "status": 201,
      "message": "User created successfully",
      "data": {
          "id": 5,
          "name": "Nina Simone",
          "email": "nina.simone@example.com"
      }
    }
    ```
- **Error Response**:
  - **Condition**: If a user with the same ID already exists.
    - **Code**: `409 Conflict`
    - **Content Example**:
        ```json
        {
        "timestamp": "2024-06-10T12:00:00",
        "status": 409,
        "error": "Conflict",
        "message": "A user with the same id already exists",
        "path": "/users"
        }
        ```

  - **Condition**: If a user with the same email already exists.
    - **Code**: `409 Conflict`
    - **Content Example**:
        ```json
        {
        "timestamp": "2024-06-10T12:00:00",
        "status": 409,
        "error": "Conflict",
        "message": "A user with the same email already exists",
        "path": "/users"
        }
        ```

### Update a User

- **URL**: `http://localhost:8080/users/{id}`
- **Method**: `PATCH`
- **Description**: Updates specific attributes of an existing user, identified by their ID. Only fields provided in the request body will be updated.
- **Parameters**:
  - **id** [integer]: The ID of the user to update.
- **Request Body Example**:
  ```json
  {
    "name": "Nina Martinez",
    "email": "nina.martinez@example.com"
  }
  ```
- **Success Response**:
  - **Code**: `200 OK`
  - **Content Example**:
    ```json
    {
      "timestamp": "2024-06-10T12:00:00",
      "status": 200,
      "message": "User created successfully",
      "data": {
          "id": 5,
          "name": "Nina Martinez",
          "email": "nina.martinez@example.com"
      }
    }
    ```
- **Error Response**:
  - **Condition**: If no user is found with the provided ID.
    - **Code**: `404 Not Found`
    - **Content Example**:
        ```json
        {
        "timestamp": "2024-06-10T12:00:00",
        "status": 404,
        "error": "Not Found",
        "message": "User not found with id 10",
        "path": "/users/10"
        }
        ```

  - **Condition**: If the new email provided is already in use by another user.
    - **Code**: `409 Conflict`
    - **Content Example**:
        ```json
        {
        "timestamp": "2024-06-10T12:00:00",
        "status": 409,
        "error": "Conflict",
        "message": "A user with the same email already exists",
        "path": "/users/5"
        }
        ```

### Delete a User

- **URL**: `http://localhost:8080/users/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a user from the database using their ID. This operation is irreversible.
- **Parameters**:
  - **id** [integer]: The ID of the user to delete.
- **Success Response**:
  - **Code**: `200 OK`
  - **Content Example**:
    ```json
    {
      "timestamp": "2024-06-10T12:00:00",
      "status": 200,
      "message": "User deleted successfully",
      "data": null
    }
    ```

- **Error Responses**:
  - **Condition**: If no user is found with the provided ID.
    - **Code**: `404 Not Found`
    - **Content Example**:
        ```json
        {
        "timestamp": "2024-06-10T12:00:00",
        "status": 404,
        "error": "Not Found",
        "message": "User not found with id 10",
        "path": "/users/10"
        }
        ```

Thank you for reviewing the Docker Exam API documentation.