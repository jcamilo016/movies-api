---
applyTo: '*.java'
---
# Java & Spring Boot Development Guidelines

**Objetivo:** mantener un código limpio, mantenible y bien documentado, aprovechando al máximo GitHub Copilot como asistente de productividad.

---

## 1. Estructura del Proyecto

- Utiliza una **estructura estándar de Spring Boot**:
  ```
  src/
    main/
      java/com/empresa/proyecto/...
      resources/
        application.yml
    test/
      java/com/empresa/proyecto/...
  ```
- Mantén la separación por **capas**:
  - `controller`: exposición de endpoints REST.
  - `service`: lógica de negocio.
  - `repository`: acceso a datos.
  - `model/entity`: entidades JPA.
  - `config`: configuración del proyecto.

- Escribe nombres de clases y métodos descriptivos (por ejemplo, `UserController`, `OrderService`, `ProductRepository`).

---

## 2. Configuración de Maven

- Define la versión de Java y dependencias de forma explícita:
  ```xml
  <properties>
      <java.version>17</java.version>
      <spring.boot.version>3.3.0</spring.boot.version>
  </properties>
  ```
- Utiliza el plugin de compilación:
  ```xml
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.11.0</version>
      <configuration>
          <source>${java.version}</source>
          <target>${java.version}</target>
      </configuration>
  </plugin>
  ```
- Agrupa dependencias por categoría (Spring, Database, Testing, Utilidades).
- Evita dependencias duplicadas y usa **`<dependencyManagement>`** cuando sea necesario.

---

## 3. Buenas Prácticas en Spring Boot

### Configuración
- Centraliza las propiedades en `application.yml`.
- Usa **profiles** (`application-dev.yml`, `application-prod.yml`) para entornos diferentes.
- Evita hardcodear credenciales o URLs; utiliza **variables de entorno** o **Spring Config Server**.

### Controladores
- Usa `@RestController` + `@RequestMapping("/api/v1/...")`.
- Devuelve objetos `ResponseEntity` con códigos HTTP adecuados (`200`, `201`, `404`, `500`).
- Documenta con **Swagger/OpenAPI**:
  ```java
  @Operation(summary = "Obtiene todos los usuarios", description = "Retorna una lista paginada de usuarios activos")
  ```

### Servicios
- Marca la lógica de negocio con `@Service`.
- Implementa **interfaces** cuando sea posible para facilitar pruebas unitarias.
- Maneja errores de negocio con **excepciones personalizadas**.

### Repositorios
- Usa `JpaRepository` o `CrudRepository`.
- Nombra los métodos siguiendo convenciones:
  ```java
  List<User> findByEmailContainingIgnoreCase(String email);
  ```
- Prefiere consultas derivadas (`findBy...`) antes que queries nativas.

---

## 4. JPA & Entidades

- Anota las entidades con `@Entity` y define una clave primaria con `@Id` + `@GeneratedValue`.
- Usa `@Table(name = "users")` para mayor claridad.
- Aplica `@Column(nullable = false, length = 100)` según restricciones.
- Define relaciones explícitas (`@OneToMany`, `@ManyToOne`) y usa `fetch = FetchType.LAZY` por defecto.
- Implementa `equals` y `hashCode` basados en el ID.

---

## 5. Pruebas Unitarias

- Usa **JUnit 5** y **Mockito**.
- Cubre controladores, servicios y repositorios con pruebas independientes.
- Anota con:
  ```java
  @SpringBootTest
  @AutoConfigureMockMvc
  ```
- Verifica respuestas REST con `MockMvc`.
- Recomendación: integra **Jacoco** para medir cobertura.

---

## 6. Buenas Prácticas de Código

- Sigue el estándar de formato **Google Java Style** o **Spring Code Style**.
- Escribe **nombres significativos** para clases, variables y métodos.
- Prefiere `Optional` en lugar de `null`.
- Evita la lógica compleja en controladores; muévela a servicios.
- Agrega **JavaDoc** a métodos públicos.
- Evita los *“god classes”*: una clase no debe tener más de 300 líneas.

---

## 7. Base de Datos en Memoria (H2)

- Usa H2 para desarrollo y pruebas:
  ```yaml
  spring:
    datasource:
      url: jdbc:h2:mem:testdb
      driverClassName: org.h2.Driver
      username: sa
      password:
    jpa:
      hibernate:
        ddl-auto: update
      show-sql: true
      database-platform: org.hibernate.dialect.H2Dialect
  ```
- Incluye scripts SQL en `src/main/resources/data.sql` para datos de prueba.

---

## 8. Swagger / OpenAPI

- Agrega dependencias:
  ```xml
  <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.5.0</version>
  </dependency>
  ```
- La documentación estará disponible en:
  ```
  http://localhost:8080/swagger-ui.html
  ```

---


## 9. Extras

- Usa **Lombok** para reducir código repetitivo (`@Getter`, `@Setter`, `@Builder`, etc.`).
- Integra **Spring Boot Actuator** para monitoreo.
- Documenta el proyecto con un `README.md` claro.
- Configura **GitHub Actions** o **Azure DevOps Pipelines** para CI/CD.

---

## Ejemplo de Setup Rápido

```bash
mvn archetype:generate   -DgroupId=com.empresa.proyecto   -DartifactId=demo-app   -DarchetypeArtifactId=maven-archetype-quickstart
```

```bash
mvn clean install
mvn spring-boot:run
```