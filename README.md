# Movies API

This is a rest api service written in java using springboot framework v3.1 and java v17.

The database engine used was a MongoDB.

## Instructions to run locally
To run this project locally on your computer, go to folder ```src/resources``` and create a file called ```.env```

Inside the file create the following environment variables, and replace each variable with its respective value:

```
MONGO_DATABASE=""
MONGO_USER=""
MONGO_PASSWORD=""
MONGO_CLUSTER=""
```

## API Documentation

### Current Endpoints
- `GET /api/v1/movies` - Retrieve all movies
- `GET /api/v1/movies/{imdbId}` - Retrieve a single movie by IMDb ID

### Planned Features
For information about planned features and enhancements, see the [docs](./docs) directory.

#### Upcoming: PATCH Endpoint
A PATCH endpoint for partial movie updates is planned. See the detailed specification in [docs/PATCH-ENDPOINT-SPECIFICATION.md](./docs/PATCH-ENDPOINT-SPECIFICATION.md) for complete information including:
- Feature description and benefits
- Detailed acceptance criteria
- API contract with examples
- Technical implementation notes

