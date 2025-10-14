package dev.jcacosta.movies.repositories;

import dev.jcacosta.movies.domains.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie();
        movie1.setImdbId("tt0111161");
        movie1.setTitle("The Shawshank Redemption");
        movie1.setReleaseDate("1994-09-23");
        movie1.setGenres(Arrays.asList("Drama", "Crime"));

        movie2 = new Movie();
        movie2.setImdbId("tt0068646");
        movie2.setTitle("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setGenres(Arrays.asList("Drama", "Crime"));
    }

    @Test
    void findByImdbId_WithExistingId_ShouldReturnMovie() {
        // Given
        entityManager.persistAndFlush(movie1);

        // When
        Optional<Movie> found = movieRepository.findByImdbId("tt0111161");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("The Shawshank Redemption");
        assertThat(found.get().getImdbId()).isEqualTo("tt0111161");
    }

    @Test
    void findByImdbId_WithNonExistingId_ShouldReturnEmpty() {
        // When
        Optional<Movie> found = movieRepository.findByImdbId("nonexistent");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    void findAll_ShouldReturnAllMovies() {
        // Given
        entityManager.persistAndFlush(movie1);
        entityManager.persistAndFlush(movie2);

        // When
        List<Movie> movies = movieRepository.findAll();

        // Then
        assertThat(movies).hasSize(2);
        assertThat(movies).extracting(Movie::getImdbId)
                .containsExactlyInAnyOrder("tt0111161", "tt0068646");
    }

    @Test
    void save_ShouldPersistMovie() {
        // When
        Movie savedMovie = movieRepository.save(movie1);

        // Then
        assertThat(savedMovie.getId()).isNotNull();
        assertThat(savedMovie.getImdbId()).isEqualTo("tt0111161");

        Movie foundMovie = entityManager.find(Movie.class, savedMovie.getId());
        assertThat(foundMovie).isNotNull();
        assertThat(foundMovie.getTitle()).isEqualTo("The Shawshank Redemption");
    }
}