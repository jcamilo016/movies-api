# 🚀 Movies API - Migración Java 21 + H2

## Resumen de Cambios Realizados

### 🔧 Actualizaciones de Tecnología
- **Java**: Actualizado de Java 17 a Java 21
- **Spring Boot**: Actualizado de 3.0.1 a 3.3.5 (última versión estable)
- **Base de Datos**: Migrado de MongoDB a H2 Database (en memoria)
- **ORM**: Cambiado de MongoDB Driver a JPA/Hibernate

### 📦 Nuevas Dependencias Agregadas
- `spring-boot-starter-data-jpa` - Para persistencia JPA
- `h2` - Base de datos H2 en memoria
- `springdoc-openapi-starter-webmvc-ui` (v2.3.0) - Documentación Swagger/OpenAPI
- `spring-boot-starter-test` con JUnit 5 y Mockito

### 🏗️ Arquitectura Actualizada

#### Entidades (Domain)
- **Movie.java**: Convertido de documento MongoDB a entidad JPA
  - Agregadas anotaciones `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
  - Configurada relación `@OneToMany` con reviews
  - Implementado `@ElementCollection` para genres y cast
- **Review.java**: Convertido de documento MongoDB a entidad JPA
  - Agregadas anotaciones JPA apropiadas
  - Configurada relación `@ManyToOne` con Movie

#### Repositorios
- **MovieRepository**: Cambiado de `MongoRepository` a `JpaRepository<Movie, Long>`
- **ReviewRepository**: Cambiado de `MongoRepository` a `JpaRepository<Review, Long>`
- Mantenidos métodos de consulta personalizados

#### Servicios
- **MovieService**: Eliminada dependencia de `MongoTemplate`, actualizado para JPA
- **ReviewService**: Refactorizado para usar repositorios JPA
- Agregadas anotaciones `@Transactional` donde es necesario

#### Controladores
- **MovieController**: Agregadas anotaciones Swagger (`@Tag`, `@Operation`, `@ApiResponse`)
- **ReviewController**: Documentación OpenAPI completa
- Mantenidos todos los endpoints existentes

### 📊 Base de Datos
- **Configuración**: H2 en memoria con consola web habilitada
- **Scripts SQL**:
  - `schema.sql`: Definición de tablas con relaciones
  - `data.sql`: Datos de muestra (5 películas con reviews)
- **Acceso**: Consola H2 disponible en `/h2-console`

### 🧪 Testing Completo
- **29 tests implementados** cubriendo:
  - Controllers (MockMvc tests)
  - Services (Unit tests con Mockito)
  - Repositories (Data JPA tests)
- **Configuración separada**: `application-test.properties` para tests
- **Cobertura**: Unit tests e integration tests

### 📚 Documentación API
- **Swagger UI**: Disponible en `/swagger-ui.html`
- **OpenAPI 3**: Documentación interactiva completa
- **Configuración**: `SwaggerConfig.java` con metadatos del API

### ⚙️ Configuración
- **Properties**: Configuración H2, JPA y Swagger
- **Exception Handling**: `GlobalExceptionHandler` para manejo centralizado de errores
- **Profiles**: Separación entre desarrollo y testing

### 🔧 Scripts de Ejecución
- **Windows**: `run.bat` y `test.bat`
- **Linux/macOS**: `run.sh` y `test.sh`
- **Maven Wrapper**: Todos los scripts usan `./mvnw` para evitar dependencia de instalación Maven

### 🎯 Endpoints Disponibles
- `GET /api/v1/movies` - Listar todas las películas
- `GET /api/v1/movies/{id}` - Obtener película por ID
- `POST /api/v1/movies` - Crear nueva película
- `PUT /api/v1/movies/{id}` - Actualizar película
- `DELETE /api/v1/movies/{id}` - Eliminar película
- `GET /api/v1/movies/{movieId}/reviews` - Reviews de una película
- `POST /api/v1/movies/{movieId}/reviews` - Crear review
- `PUT /api/v1/reviews/{id}` - Actualizar review
- `DELETE /api/v1/reviews/{id}` - Eliminar review

### 🌐 URLs de Acceso
- **API**: `http://localhost:8080/api/v1`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **H2 Console**: `http://localhost:8080/h2-console`
  - **JDBC URL**: `jdbc:h2:mem:moviesdb`
  - **Username**: `sa`
  - **Password**: (vacío)

### ✅ Estado del Proyecto
- ✅ Compilación exitosa
- ✅ 29/29 tests pasando
- ✅ Documentación API completa
- ✅ Scripts de ejecución funcionando
- ✅ Base de datos inicializada con datos de muestra

### 🔄 Comandos de Ejecución

#### Windows
```batch
# Ejecutar tests
cd scripts
.\test.bat

# Ejecutar aplicación
cd scripts
.\run.bat
```

#### Linux/macOS
```bash
# Ejecutar tests
cd scripts
./test.sh

# Ejecutar aplicación
cd scripts
./run.sh
```

#### Maven Directo
```bash
# Tests
./mvnw test

# Ejecutar aplicación
./mvnw spring-boot:run
```

---

**La migración ha sido completada exitosamente**, manteniendo toda la funcionalidad original mientras se moderniza la stack tecnológica con Java 21, H2 Database, y documentación Swagger completa.