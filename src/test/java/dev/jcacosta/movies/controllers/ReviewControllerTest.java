package dev.jcacosta.movies.controllers;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.domains.Review;
import dev.jcacosta.movies.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie;
    private Review review1;
    private Review review2;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setImdbId("tt0111161");
        movie.setTitle("The Shawshank Redemption");

        review1 = new Review();
        review1.setId(1L);
        review1.setBody("Great movie!");
        review1.setMovie(movie);

        review2 = new Review();
        review2.setId(2L);
        review2.setBody("Amazing storytelling!");
        review2.setMovie(movie);
    }

    @Test
    void createReview_WithValidPayload_ShouldCreateReview() throws Exception {
        // Given
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "Great movie!");
        payload.put("imdbId", "tt0111161");

        when(reviewService.createReview("Great movie!", "tt0111161")).thenReturn(review1);

        // When & Then
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.body").value("Great movie!"));
    }

    @Test
    void createReview_WithInvalidMovieId_ShouldReturnInternalServerError() throws Exception {
        // Given
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "Good movie!");
        payload.put("imdbId", "invalid");

        when(reviewService.createReview("Good movie!", "invalid"))
                .thenThrow(new RuntimeException("Movie not found with imdbId: invalid"));

        // When & Then
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void getReviewsByMovieImdbId_ShouldReturnReviews() throws Exception {
        // Given
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewService.getReviewsByMovieImdbId("tt0111161")).thenReturn(reviews);

        // When & Then
        mockMvc.perform(get("/api/v1/reviews/movie/tt0111161")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].body").value("Great movie!"))
                .andExpect(jsonPath("$[1].body").value("Amazing storytelling!"));
    }

    @Test
    void getReviewsByMovieImdbId_WithNoReviews_ShouldReturnEmptyList() throws Exception {
        // Given
        when(reviewService.getReviewsByMovieImdbId("tt0000000")).thenReturn(Arrays.asList());

        // When & Then
        mockMvc.perform(get("/api/v1/reviews/movie/tt0000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getAllReviews_ShouldReturnAllReviews() throws Exception {
        // Given
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewService.getAllReviews()).thenReturn(reviews);

        // When & Then
        mockMvc.perform(get("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].body").value("Great movie!"))
                .andExpect(jsonPath("$[1].body").value("Amazing storytelling!"));
    }
}