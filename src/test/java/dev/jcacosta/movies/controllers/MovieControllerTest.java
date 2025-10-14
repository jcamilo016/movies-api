package dev.jcacosta.movies.controllers;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private Movie movie1;
    private Movie movie2;
    private List<Movie> movieList;

    @BeforeEach
    void setUp() {
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setImdbId("tt0111161");
        movie1.setTitle("The Shawshank Redemption");
        movie1.setReleaseDate("1994-09-23");
        movie1.setGenres(Arrays.asList("Drama", "Crime"));

        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setImdbId("tt0068646");
        movie2.setTitle("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setGenres(Arrays.asList("Drama", "Crime"));

        movieList = Arrays.asList(movie1, movie2);
    }

    @Test
    void getAllMovies_ShouldReturnListOfMovies() throws Exception {
        // Given
        when(movieService.getAllMovies()).thenReturn(movieList);

        // When & Then
        mockMvc.perform(get("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].imdbId").value("tt0111161"))
                .andExpect(jsonPath("$[0].title").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[1].imdbId").value("tt0068646"))
                .andExpect(jsonPath("$[1].title").value("The Godfather"));
    }

    @Test
    void getAllMovies_WhenNoMovies_ShouldReturnEmptyList() throws Exception {
        // Given
        when(movieService.getAllMovies()).thenReturn(Arrays.asList());

        // When & Then
        mockMvc.perform(get("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getMovieById_WithValidId_ShouldReturnMovie() throws Exception {
        // Given
        when(movieService.getMovieById("tt0111161")).thenReturn(Optional.of(movie1));

        // When & Then
        mockMvc.perform(get("/api/v1/movies/tt0111161")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imdbId").value("tt0111161"))
                .andExpect(jsonPath("$.title").value("The Shawshank Redemption"));
    }

    @Test
    void getMovieById_WithInvalidId_ShouldReturnEmptyOptional() throws Exception {
        // Given
        when(movieService.getMovieById("invalid")).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/v1/movies/invalid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}