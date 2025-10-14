package dev.jcacosta.movies.controllers;

import dev.jcacosta.movies.domains.Review;
import dev.jcacosta.movies.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Reviews", description = "API for managing movie reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Operation(summary = "Create a new review", description = "Creates a new review for a specific movie identified by IMDB ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Review> createReview(
            @Parameter(description = "Review payload containing reviewBody and imdbId") @RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(
                reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get reviews by movie IMDB ID", description = "Retrieves all reviews for a specific movie identified by IMDB ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reviews"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movie/{imdbId}")
    public ResponseEntity<List<Review>> getReviewsByMovieImdbId(
            @Parameter(description = "IMDB ID of the movie", example = "tt0111161") @PathVariable String imdbId) {
        return new ResponseEntity<>(reviewService.getReviewsByMovieImdbId(imdbId), HttpStatus.OK);
    }

    @Operation(summary = "Get all reviews", description = "Retrieves all reviews in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all reviews"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }
}
