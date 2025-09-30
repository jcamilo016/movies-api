# Movies API

A RESTful API service for managing movies and reviews, built with modern Java technologies.

## Project Overview

This is a movies management API that allows users to retrieve movie information and create reviews. The application provides endpoints to:
- Get all movies
- Get a specific movie by IMDB ID
- Create reviews for movies

### Technologies Used

- **Java 17** - Programming language
- **Spring Boot 3.0.1** - Application framework
- **MongoDB** - NoSQL database for storing movies and reviews
- **Maven** - Build and dependency management
- **Lombok** - Reduce boilerplate code
- **Spring Data MongoDB** - Database integration

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher ([Download JDK](https://adoptium.net/))
- **Maven 3.8+** (or use the included Maven wrapper `./mvnw`)
- **MongoDB** instance (local installation or MongoDB Atlas account)

## Local Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/jcamilo016/movies-api.git
cd movies-api
```

### 2. Configure Environment Variables

Create a `.env` file in the `src/main/resources` directory:

```bash
# Navigate to resources directory
cd src/main/resources

# Create the .env file
touch .env
```

Add the following environment variables to the `.env` file:

```properties
MONGO_DATABASE=your_database_name
MONGO_USER=your_mongodb_username
MONGO_PASSWORD=your_mongodb_password
MONGO_CLUSTER=your_mongodb_cluster_url
```

**Example for MongoDB Atlas:**
```properties
MONGO_DATABASE=movies_db
MONGO_USER=admin
MONGO_PASSWORD=yourSecurePassword123
MONGO_CLUSTER=cluster0.abc123.mongodb.net
```

**Note:** For local MongoDB, adjust the connection string in `application.properties` accordingly.

### 3. Build the Application

Return to the project root directory and build the application:

```bash
# Return to project root
cd ../..

# Build using Maven wrapper (recommended)
./mvnw clean install

# Or using Maven (if installed globally)
mvn clean install
```

### 4. Run the Application

Start the application using one of the following methods:

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Using Maven (if installed globally)
mvn spring-boot:run

# Or run the JAR file directly
java -jar target/movies-0.0.1.jar
```

The API will start on `http://localhost:8080`

### 5. Test the API

Once the application is running, you can test the endpoints:

```bash
# Get all movies
curl http://localhost:8080/api/v1/movies

# Get a specific movie by IMDB ID
curl http://localhost:8080/api/v1/movies/{imdbId}

# Create a review
curl -X POST http://localhost:8080/api/v1/reviews \
  -H "Content-Type: application/json" \
  -d '{"reviewBody": "Great movie!", "imdbId": "tt1234567"}'
```

## Docker Setup

### Prerequisites for Docker

- **Docker** installed ([Download Docker](https://www.docker.com/get-started))
- **Docker Compose** (optional, for orchestrating multiple containers)

### Build Docker Image

1. First, build the application JAR file:

```bash
./mvnw clean package -DskipTests
```

2. Build the Docker image:

```bash
docker build -t movies-api:latest .
```

### Run with Docker

Run the container with environment variables:

```bash
docker run -d \
  -p 8080:8080 \
  -e MONGO_DATABASE=your_database_name \
  -e MONGO_USER=your_mongodb_username \
  -e MONGO_PASSWORD=your_mongodb_password \
  -e MONGO_CLUSTER=your_mongodb_cluster_url \
  --name movies-api \
  movies-api:latest
```

### Using Environment File with Docker

Alternatively, create a `.env` file in your project root:

```properties
MONGO_DATABASE=your_database_name
MONGO_USER=your_mongodb_username
MONGO_PASSWORD=your_mongodb_password
MONGO_CLUSTER=your_mongodb_cluster_url
```

Then run:

```bash
docker run -d \
  -p 8080:8080 \
  --env-file .env \
  --name movies-api \
  movies-api:latest
```

### Docker Commands

```bash
# View running containers
docker ps

# View container logs
docker logs movies-api

# Stop the container
docker stop movies-api

# Remove the container
docker rm movies-api

# Remove the image
docker rmi movies-api:latest
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/movies` | Get all movies |
| GET | `/api/v1/movies/{imdbId}` | Get a specific movie by IMDB ID |
| POST | `/api/v1/reviews` | Create a review for a movie |

## Project Structure

```
movies-api/
├── src/
│   ├── main/
│   │   ├── java/dev/jcacosta/movies/
│   │   │   ├── controllers/      # REST controllers
│   │   │   ├── domains/           # Entity models
│   │   │   ├── repositories/      # Data access layer
│   │   │   ├── services/          # Business logic
│   │   │   └── MoviesApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── .env              # Environment variables (create this)
│   └── test/                     # Test files
├── dockerfile                    # Docker configuration
├── pom.xml                       # Maven configuration
└── README.md
```

## Contributing

Feel free to submit issues and pull requests to improve the project.

## License

This project is open source and available for educational purposes.

