# Feature Request: PATCH Endpoint for Movies

## Issue Title
Implement PATCH endpoint for partial movie updates

## Priority
Medium

## Labels
`enhancement`, `api`, `backend`

---

## Description

As a Movies API user, I need the ability to partially update movie records without having to send the entire movie object in the request. Currently, the API only supports GET operations for movies. A PATCH endpoint would allow for efficient partial updates to movie attributes such as title, release date, genres, posters, backdrops, and trailer links.

### Current State
The Movies API currently supports:
- `GET /api/v1/movies` - Retrieve all movies
- `GET /api/v1/movies/{imdbId}` - Retrieve a single movie by IMDb ID

### Proposed Enhancement
Add a new PATCH endpoint that allows partial updates to movie records:
- `PATCH /api/v1/movies/{imdbId}` - Update specific fields of a movie

### Benefits
1. **Efficiency**: Only send the fields that need to be updated, reducing payload size
2. **Flexibility**: Update one or multiple fields in a single request
3. **REST Compliance**: Follows REST API best practices for partial resource updates
4. **Data Integrity**: Reduces risk of accidentally overwriting fields when only updating specific attributes

---

## Acceptance Criteria

### 1. Endpoint Implementation
- [ ] **AC1.1**: PATCH endpoint is implemented at `/api/v1/movies/{imdbId}`
- [ ] **AC1.2**: Endpoint accepts IMDb ID as a path variable
- [ ] **AC1.3**: Endpoint returns appropriate HTTP status codes:
  - `200 OK` - Movie successfully updated
  - `404 NOT FOUND` - Movie with specified IMDb ID does not exist
  - `400 BAD REQUEST` - Invalid request body or validation errors

### 2. Updatable Fields
- [ ] **AC2.1**: The following fields can be updated independently:
  - `title` (String)
  - `releaseDate` (String)
  - `trailerLink` (String)
  - `poster` (String)
  - `genres` (List<String>)
  - `backdrops` (List<String>)
- [ ] **AC2.2**: Fields not included in the request body remain unchanged
- [ ] **AC2.3**: The `id`, `imdbId`, and `reviewIds` fields cannot be modified via PATCH

### 3. Request/Response Format
- [ ] **AC3.1**: Request body accepts JSON with one or more updatable fields
- [ ] **AC3.2**: Response body returns the complete updated movie object
- [ ] **AC3.3**: Content-Type header is `application/json`

### 4. Validation
- [ ] **AC4.1**: Empty or null values are handled appropriately
- [ ] **AC4.2**: Invalid field names are rejected with appropriate error message
- [ ] **AC4.3**: Data type validation is enforced for all fields

### 5. Service Layer
- [ ] **AC5.1**: MovieService class includes a method for partial updates
- [ ] **AC5.2**: Service method accepts IMDb ID and a Map/DTO of fields to update
- [ ] **AC5.3**: Service method returns Optional<Movie> with the updated movie

### 6. Testing
- [ ] **AC6.1**: Unit tests for MovieService.updateMovie() method
- [ ] **AC6.2**: Integration tests for PATCH endpoint
- [ ] **AC6.3**: Test cases cover:
  - Successful single field update
  - Successful multiple field update
  - Movie not found scenario
  - Invalid field name scenario
  - Empty request body scenario

---

## API Contract

### Request
```http
PATCH /api/v1/movies/{imdbId}
Content-Type: application/json

{
  "title": "Updated Movie Title",
  "genres": ["Action", "Thriller"]
}
```

### Response (Success - 200 OK)
```json
{
  "id": "507f1f77bcf86cd799439011",
  "imdbId": "tt1234567",
  "title": "Updated Movie Title",
  "releaseDate": "2023-05-15",
  "trailerLink": "https://example.com/trailer",
  "poster": "https://example.com/poster.jpg",
  "genres": ["Action", "Thriller"],
  "backdrops": ["https://example.com/backdrop1.jpg"],
  "reviewIds": [...]
}
```

### Response (Not Found - 404)
```json
{
  "timestamp": "2025-10-23T13:18:43.634Z",
  "status": 404,
  "error": "Not Found",
  "message": "Movie with IMDb ID tt1234567 not found",
  "path": "/api/v1/movies/tt1234567"
}
```

### Response (Bad Request - 400)
```json
{
  "timestamp": "2025-10-23T13:18:43.634Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid field: imdbId cannot be modified",
  "path": "/api/v1/movies/tt1234567"
}
```

---

## Technical Implementation Notes

### 1. Controller Layer
Update `MovieController.java` to include:
```java
@PatchMapping("/{imdbId}")
public ResponseEntity<Movie> updateMovie(
    @PathVariable("imdbId") String imdbId,
    @RequestBody Map<String, Object> updates
) {
    // Implementation
}
```

### 2. Service Layer
Update `MovieService.java` to include:
```java
public Optional<Movie> updateMovie(String imdbId, Map<String, Object> updates) {
    // Fetch existing movie
    // Apply partial updates
    // Save and return updated movie
}
```

### 3. Repository Layer
The existing `MovieRepository` should support the update operation through the standard `save()` method provided by `MongoRepository`.

### 4. Dependencies
No new dependencies are required. The implementation will use:
- Spring Web for `@PatchMapping`
- Spring Data MongoDB for persistence
- Lombok for entity management

---

## Out of Scope
The following are NOT included in this feature:
- Creating new movies (POST endpoint) - separate feature
- Deleting movies (DELETE endpoint) - separate feature
- Bulk update operations
- Update of review associations (should be handled through Review endpoints)
- Authentication/Authorization (to be addressed separately)

---

## Related Documentation
- [REST API Design: PATCH vs PUT](https://restfulapi.net/http-methods/#patch)
- [Spring Data MongoDB Documentation](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/)
- [RFC 5789 - PATCH Method for HTTP](https://tools.ietf.org/html/rfc5789)

---

## Estimated Effort
**Story Points**: 3-5 (depending on team velocity)
**Estimated Time**: 4-8 hours

### Breakdown:
- Controller implementation: 1-2 hours
- Service layer logic: 1-2 hours
- Unit tests: 1-2 hours
- Integration tests: 1-2 hours
- Documentation updates: 0.5-1 hour
- Code review and refinement: 0.5-1 hour
