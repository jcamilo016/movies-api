---
name: PATCH Endpoint for Movies
about: Implement a PATCH endpoint to allow partial updates to movie records
title: '[FEATURE] Add PATCH endpoint for movies'
labels: enhancement, api, backend
assignees: ''
---

## Description

Implement a PATCH endpoint that allows partial updates to movie records in the Movies API. This endpoint will enable users to update specific fields of a movie without sending the entire movie object.

### Current State
The API currently only supports:
- `GET /api/v1/movies` - Retrieve all movies
- `GET /api/v1/movies/{imdbId}` - Retrieve a single movie by IMDb ID

### Proposed Change
Add `PATCH /api/v1/movies/{imdbId}` endpoint for partial movie updates.

## Benefits
- Efficient updates by sending only changed fields
- Reduced payload size
- Better REST API compliance
- Improved data integrity

## Acceptance Criteria

### Endpoint Implementation
- [ ] PATCH endpoint implemented at `/api/v1/movies/{imdbId}`
- [ ] Accepts IMDb ID as path variable
- [ ] Returns appropriate HTTP status codes (200, 404, 400)

### Updatable Fields
- [ ] Can update: `title`, `releaseDate`, `trailerLink`, `poster`, `genres`, `backdrops`
- [ ] Cannot update: `id`, `imdbId`, `reviewIds`
- [ ] Unspecified fields remain unchanged

### Request/Response
- [ ] Accepts JSON request body with partial fields
- [ ] Returns complete updated movie object
- [ ] Content-Type: application/json

### Validation
- [ ] Handles empty/null values appropriately
- [ ] Rejects invalid field names
- [ ] Enforces data type validation

### Testing
- [ ] Unit tests for service layer
- [ ] Integration tests for endpoint
- [ ] Test scenarios: single field update, multiple fields update, not found, invalid fields

## API Example

### Request
```http
PATCH /api/v1/movies/tt1234567
Content-Type: application/json

{
  "title": "Updated Movie Title",
  "genres": ["Action", "Thriller"]
}
```

### Success Response (200)
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

## Technical Notes

**Implementation locations:**
- Controller: `dev.jcacosta.movies.controllers.MovieController`
- Service: `dev.jcacosta.movies.services.MovieService`
- Repository: Existing `MovieRepository` (no changes needed)

**Dependencies:** No new dependencies required

## Estimated Effort
**Story Points:** 3-5  
**Time Estimate:** 4-8 hours

## Additional Context
For detailed technical specification, see: `/docs/PATCH-ENDPOINT-SPECIFICATION.md`
