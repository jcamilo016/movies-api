# Movies API 🎬

A simple REST API for managing movies and reviews, built with **Spring Boot 3.3.5** and **Java 21**. The API uses **H2 in-memory database** for development and testing, with **JPA/Hibernate** for data persistence.

## ✨ Features

- 🎭 **Movie Management**: Create, read, update, and delete movies
- 📝 **Review System**: Add and manage movie reviews
- 📚 **Interactive API Documentation**: Swagger/OpenAPI integration
- 💾 **H2 Database**: In-memory database for easy development
- 🧪 **Comprehensive Testing**: Unit and integration tests
- 🚀 **Easy Setup**: Simple scripts to run and test

## 🛠️ Tech Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.3.5** - Web framework
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Swagger/OpenAPI** - API documentation
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **Maven** - Build and dependency management

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Git**

### 🎯 Running the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/jcamilo016/movies-api.git
   cd movies-api
   ```

2. **Run using the provided scripts:**

   **On Windows:**
   ```cmd
   scripts\run.bat
   ```

   **On Linux/Mac:**
   ```bash
   chmod +x scripts/*.sh
   ./scripts/run.sh
   ```

   **Or manually with Maven:**
   ```bash
   mvn clean spring-boot:run
   ```

3. **Access the application:**
   - **API Base URL**: http://localhost:8080/api/v1
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **H2 Console**: http://localhost:8080/h2-console

### 🧪 Running Tests

**Using scripts:**
```bash
# Windows
scripts\test.bat

# Linux/Mac
./scripts/test.sh

# Or manually with Maven
mvn test
```

## 📖 API Endpoints

### Movies
- `GET /api/v1/movies` - Get all movies
- `GET /api/v1/movies/{imdbId}` - Get movie by IMDB ID

### Reviews
- `GET /api/v1/reviews` - Get all reviews
- `GET /api/v1/reviews/movie/{imdbId}` - Get reviews for a specific movie
- `POST /api/v1/reviews` - Create a new review

### Example Review Creation
```json
POST /api/v1/reviews
Content-Type: application/json

{
  "reviewBody": "Amazing movie!",
  "imdbId": "tt0111161"
}
```

## 🗄️ Database

The application uses **H2 in-memory database** with pre-loaded sample data including:
- 8 popular movies (Shawshank Redemption, The Godfather, etc.)
- Sample reviews for each movie
- Movie genres and backdrop images

### H2 Console Access
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(empty)*

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/dev/jcacosta/movies/
│   │   ├── config/          # Configuration classes
│   │   ├── controllers/     # REST controllers
│   │   ├── domains/         # JPA entities
│   │   ├── repositories/    # Data repositories
│   │   └── services/        # Business logic
│   └── resources/
│       ├── application.properties
│       ├── schema.sql       # Database schema
│       └── data.sql         # Sample data
├── test/                    # Unit and integration tests
└── scripts/                 # Run and test scripts
```

## 🧪 Testing

The project includes comprehensive testing:

- **Unit Tests**: Controllers, Services, and Repositories
- **Integration Tests**: Full application context tests
- **Test Coverage**: Aims for 80%+ coverage
- **Test Database**: Uses H2 in-memory database

### Test Classes
- `MovieControllerTest` - REST API tests
- `MovieServiceTest` - Business logic tests
- `MovieRepositoryTest` - Data layer tests
- `ReviewControllerTest` - Review API tests
- `ReviewServiceTest` - Review business logic tests
- `ReviewRepositoryTest` - Review data layer tests

## 🔧 Development

### Building the Project
```bash
mvn clean compile
```

### Running Tests with Coverage
```bash
mvn clean test
```

### Packaging
```bash
mvn clean package
```

## 📚 API Documentation

Once the application is running, visit the **Swagger UI** at:
http://localhost:8080/swagger-ui.html

The interactive documentation provides:
- Complete API reference
- Request/response examples
- Try-it-out functionality
- Schema definitions

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Juan Acosta** - [GitHub](https://github.com/jcamilo016)

---

⭐ **Star this repository if you found it helpful!**

