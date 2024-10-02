# AI Chat Spring Boot Application

## Overview

This is a simple Spring Boot project that integrates with OpenAI to provide unstructured and structured responses via HTTP endpoints. The application is built with **Java 21** and **Spring Boot 3.2.10**, using Maven as the build tool. It includes endpoints to return simple chat responses, lists, maps, and structured movie information from the OpenAI API.

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.2.10
- **Maven**: Project build and dependency management
- **OpenAI API**: For generating responses

## Dependencies

The project uses the following key dependencies:

- **Spring Web**: For building REST APIs.
- **Spring AI (OpenAI)**: For interacting with the OpenAI API.

Here are the Maven dependencies required:

```xml
<dependencies>
    <!-- Spring Boot Web Dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- OpenAI Dependency (Hypothetical, update with actual dependency) -->
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-openai</artifactId>
        <version>1.0.0</version>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Prerequisites

1. **Java 21** installed on your machine.
2. **Maven** installed for building the project.
3. A valid **OpenAI API key**.

## Setup Instructions

### Step 1: Clone the Repository

```bash
git clone https://github.com/LegPro/springai-demo.git
cd springai-demo
```

### Step 2: Configure OpenAI API Key

The application requires an OpenAI API key for making requests. You can set the API key as an environment variable or in the `application.properties` file.

#### Option 1: Set Environment Variable

On **Linux/macOS**:
```bash
export SPRING_AI_OPENAI_API_KEY=your-openai-api-key
```

On **Windows**:
```bash
set SPRING_AI_OPENAI_API_KEY=your-openai-api-key
```

#### Option 2: Add API Key in `application.properties`

You can also add the API key directly in the `src/main/resources/application.properties` file:

```properties
spring.ai.openai.api-key=your-openai-api-key
```

### Step 3: Build the Project

You can build the project using Maven:

```bash
mvn clean install
```

### Step 4: Run the Application

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

Once the application starts, it will be accessible at `http://localhost:8080`.

## Available Endpoints

### 1. `/chat`
- **Description**: Provides a simple text response for a provided message.
- **Method**: `GET`
- **Default Message**: "Give me names of five cities in US"
- **Example**:
    ```bash
    curl "http://localhost:8080/chat"
    ```

### 2. `/chatlist`
- **Description**: Provides a list of items as a response (default: five cities in the US).
- **Method**: `GET`
- **Example**:
    ```bash
    curl "http://localhost:8080/chatlist"
    ```

### 3. `/chatmap`
- **Description**: Returns a map of countries and their capitals.
- **Method**: `GET`
- **Example**:
    ```bash
    curl "http://localhost:8080/chatmap"
    ```

### 4. `/director-movie-service`
- **Description**: Generates a list of movies directed by a specified director in JSON format.
- **Method**: `GET`
- **Query Param**: `directorName` (default: "Steven Spielberg")
- **Example**:
    ```bash
    curl "http://localhost:8080/director-movie-service?directorName=Christopher%20Nolan"
    ```

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── demo
│   │   │               ├── controller
│   │   │               │   └── AiController.java
│   │   │               └── modal
│   │   │                   └── Movie.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
├── pom.xml
└── README.md
```

- **AiController.java**: Contains the REST API endpoints to interact with OpenAI.
- **Movie.java**: A model class representing a movie with fields for title and release year.
- **application.properties**: Configuration file for Spring Boot.

## Building with Docker (Optional)

If you prefer to run the application in a Docker container, you can follow these steps:

### Step 1: Create Dockerfile

```dockerfile
FROM openjdk:21-jdk-alpine
COPY target/ai-chat-spring-boot-app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Step 2: Build and Run the Docker Image

1. **Build the Docker image**:
   ```bash
   docker build -t ai-chat-app .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -p 8080:8080 -e SPRING_AI_OPENAI_API_KEY=your-openai-api-key ai-chat-app
   ```

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.


