package dev.jcacosta.movies.services;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setImdbId("tt0111161");
        movie1.setTitle("The Shawshank Redemption");
        movie1.setReleaseDate("1994-09-23");

        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setImdbId("tt0068646");
        movie2.setTitle("The Godfather");
        movie2.setReleaseDate("1972-03-24");
    }

    @Test
    void getAllMovies_ShouldReturnAllMovies() {
        // Given
        List<Movie> expectedMovies = Arrays.asList(movie1, movie2);
        when(movieRepository.findAll()).thenReturn(expectedMovies);

        // When
        List<Movie> actualMovies = movieService.getAllMovies();

        // Then
        assertThat(actualMovies).hasSize(2);
        assertThat(actualMovies).containsExactly(movie1, movie2);
    }

    @Test
    void getAllMovies_WhenNoMovies_ShouldReturnEmptyList() {
        // Given
        when(movieRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<Movie> actualMovies = movieService.getAllMovies();

        // Then
        assertThat(actualMovies).isEmpty();
    }

    @Test
    void getMovieById_WithExistingId_ShouldReturnMovie() {
        // Given
        when(movieRepository.findByImdbId("tt0111161")).thenReturn(Optional.of(movie1));

        // When
        Optional<Movie> actualMovie = movieService.getMovieById("tt0111161");

        // Then
        assertThat(actualMovie).isPresent();
        assertThat(actualMovie.get().getImdbId()).isEqualTo("tt0111161");
        assertThat(actualMovie.get().getTitle()).isEqualTo("The Shawshank Redemption");
    }

    @Test
    void getMovieById_WithNonExistingId_ShouldReturnEmpty() {
        // Given
        when(movieRepository.findByImdbId("nonexistent")).thenReturn(Optional.empty());

        // When
        Optional<Movie> actualMovie = movieService.getMovieById("nonexistent");

        // Then
        assertThat(actualMovie).isEmpty();
    }

    @Test
    void createMovie_WithValidData_ShouldCreateMovie() {
        // Given
        Movie newMovie = new Movie();
        newMovie.setImdbId("tt9999999");
        newMovie.setTitle("New Test Movie");
        newMovie.setReleaseDate("2025-01-01");

        when(movieRepository.findByImdbId("tt9999999")).thenReturn(Optional.empty());
        when(movieRepository.save(newMovie)).thenReturn(newMovie);

        // When
        Movie createdMovie = movieService.createMovie(newMovie);

        // Then
        assertThat(createdMovie).isNotNull();
        assertThat(createdMovie.getImdbId()).isEqualTo("tt9999999");
        assertThat(createdMovie.getTitle()).isEqualTo("New Test Movie");
    }

    @Test
    void createMovie_WithMissingTitle_ShouldThrowException() {
        // Given
        Movie newMovie = new Movie();
        newMovie.setImdbId("tt9999999");
        newMovie.setTitle(null);

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movieService.createMovie(newMovie);
        });
    }

    @Test
    void createMovie_WithEmptyTitle_ShouldThrowException() {
        // Given
        Movie newMovie = new Movie();
        newMovie.setImdbId("tt9999999");
        newMovie.setTitle("   ");

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movieService.createMovie(newMovie);
        });
    }

    @Test
    void createMovie_WithMissingImdbId_ShouldThrowException() {
        // Given
        Movie newMovie = new Movie();
        newMovie.setTitle("New Movie");
        newMovie.setImdbId(null);

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movieService.createMovie(newMovie);
        });
    }

    @Test
    void createMovie_WithDuplicateImdbId_ShouldThrowException() {
        // Given
        Movie existingMovie = new Movie();
        existingMovie.setImdbId("tt0111161");
        existingMovie.setTitle("Existing Movie");

        Movie newMovie = new Movie();
        newMovie.setImdbId("tt0111161");
        newMovie.setTitle("New Movie with Duplicate ID");

        when(movieRepository.findByImdbId("tt0111161")).thenReturn(Optional.of(existingMovie));

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movieService.createMovie(newMovie);
        });
    }
}