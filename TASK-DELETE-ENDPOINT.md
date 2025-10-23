# Task: Implement DELETE Movie Endpoint

## Description

Develop a new DELETE endpoint for the Movies API that allows removal of movies from the database. This endpoint should follow RESTful best practices and integrate seamlessly with the existing API architecture.

The DELETE endpoint will enable administrators and authorized users to remove movies from the system by their unique identifier (IMDB ID). This functionality is essential for maintaining data quality and managing the movie catalog effectively.

## Technical Requirements

- **Endpoint Path**: `DELETE /api/v1/movies/{imdbId}`
- **Path Parameter**: `imdbId` (String) - The IMDB identifier of the movie to delete
- **HTTP Method**: DELETE
- **Framework**: Spring Boot 3.1 with Java 17
- **Database**: MongoDB

## Implementation Details

### Controller Layer
- Add a new method in `MovieController.java` to handle DELETE requests
- Use `@DeleteMapping` annotation with path variable `/{imdbId}`
- Return appropriate HTTP status codes:
  - `204 NO CONTENT` - When movie is successfully deleted
  - `404 NOT FOUND` - When movie with given imdbId does not exist
  - `500 INTERNAL SERVER ERROR` - For any server-side errors

### Service Layer
- Add a `deleteMovie(String imdbId)` method in `MovieService.java`
- Implement business logic to verify movie existence before deletion
- Return a boolean or void to indicate operation success

### Repository Layer
- Leverage existing `MovieRepository` methods
- Use `findByImdbId()` to verify existence
- Use `delete()` or `deleteById()` for removal operation

## Acceptance Criteria

- [ ] DELETE endpoint is accessible at `/api/v1/movies/{imdbId}`
- [ ] Endpoint successfully deletes a movie when valid imdbId is provided
- [ ] Endpoint returns HTTP 204 (No Content) on successful deletion
- [ ] Endpoint returns HTTP 404 (Not Found) when imdbId does not exist in database
- [ ] Movie is completely removed from MongoDB database after successful deletion
- [ ] Any associated reviews are handled appropriately (either deleted or left orphaned based on business rules)
- [ ] Endpoint handles invalid imdbId format gracefully
- [ ] Unit tests are written for the service layer
- [ ] Integration tests are written for the controller layer
- [ ] API documentation is updated (if applicable)
- [ ] No breaking changes to existing GET endpoints
- [ ] Code follows existing project conventions and style
- [ ] Changes are reviewed and approved via pull request

## Testing Strategy

### Unit Tests
- Test `MovieService.deleteMovie()` with existing movie
- Test `MovieService.deleteMovie()` with non-existent movie
- Test exception handling in service layer

### Integration Tests
- Test DELETE request with valid imdbId returns 204
- Test DELETE request with non-existent imdbId returns 404
- Test that movie is actually removed from database
- Test idempotency (deleting same movie twice)

## Related Components

- `MovieController.java` - Add DELETE endpoint handler
- `MovieService.java` - Add delete business logic
- `MovieRepository.java` - Use existing repository methods
- `Movie.java` - Domain model (no changes expected)

## Dependencies

- Existing MongoDB configuration
- Spring Boot Web
- Spring Data MongoDB
- Lombok (for existing patterns)

## Notes

- Consider implementing soft delete vs hard delete based on business requirements
- If implementing soft delete, add a `deleted` flag to Movie entity
- Consider the relationship with Review entities and decide on cascade delete strategy
- Ensure proper logging for delete operations for audit purposes
- Consider adding authorization/authentication checks if not already present at API level

## Priority

Medium - This is a standard CRUD operation that completes the basic API functionality

## Estimated Effort

2-3 hours (including tests and documentation)
