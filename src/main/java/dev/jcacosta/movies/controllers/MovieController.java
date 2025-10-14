package dev.jcacosta.movies.controllers;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movies", description = "API for managing movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Operation(summary = "Get all movies", description = "Retrieves a list of all available movies in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of movies"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @Operation(summary = "Get movie by IMDB ID", description = "Retrieves a specific movie by its IMDB identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the movie"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(
            @Parameter(description = "IMDB ID of the movie to retrieve", example = "tt0111161") @PathVariable("id") String imdbId) {
        return new ResponseEntity<>(movieService.getMovieById(imdbId), HttpStatus.OK);
    }
}
