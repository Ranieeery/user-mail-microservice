# Microservices Project

This repository contains multiple microservices built with Spring Boot and Maven. It includes two main services:

- **Email Service**: Manages email sending using SMTP and integrates with RabbitMQ for messaging.
- **User Service**: Handles user management and publishes messages to trigger email notifications.

Both services are containerized and can be deployed using Docker.

## Project Structure

- **docker-compose.yml**: Orchestrates container deployment.
- **email/**: Contains the Email service, including its Maven project, source code, Dockerfile, and configuration.
- **user/**: Contains the User service, including its Maven project, source code, Dockerfile, and configuration.
- **postgres-init/**: Contains initialization SQL scripts for PostgreSQL.

## Prerequisites

- Java 17 (JDK 17)
- Maven
- Docker and Docker Compose

## Technologies

- Java 17 (JDK 17)
- Maven
- Spring Boot
- Spring Data JPA
- Spring AMQP
- Spring Mail
- Spring HATEOAS
- PostgreSQL
- RabbitMQ
- Docker

## Configuration

Configuration files are located under the `src/main/resources` directory of each service. Common environment variables are defined in `.envexample` at the root of the project. Make sure to set up the following variables:

```sh
DB_USERNAME=
POSTGRES_PASSWORD=

AMQP_STRING_CONNECTION=https://www.cloudamqp.com/

EMAIL=
APP_PASSWORD=https://myaccount.google.com/apppasswords

POSTGRES_PORT=5432
USER_PORT=8081
EMAIL_PORT=8082

POSTGRES_USER_PORT=5432
POSTGRES_EMAIL_PORT=5432
```

## Building and Running

### Using Maven

Each service includes Maven wrapper scripts (`mvnw` and `mvnw.cmd`) to build and run the application.

For Email Service:

```sh
cd email
./mvnw clean install
./mvnw spring-boot:run
```

For User Service:

```sh
cd user
./mvnw clean install
./mvnw spring-boot:run
```

### Using Docker

Both services are containerized using Docker. You can build and run them individually using their respective `Dockerfile`.

Alternatively, use the provided `docker-compose.yml` to run all services together:

```sh
docker-compose up --build
```

## Additional Information

- **Email Service** integrates with RabbitMQ and PostgreSQL.
- **User Service** consumes REST calls and publishes email notifications.
