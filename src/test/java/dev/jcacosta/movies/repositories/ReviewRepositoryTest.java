package dev.jcacosta.movies.repositories;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.domains.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ReviewRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository reviewRepository;

    private Movie movie;
    private Review review1;
    private Review review2;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setImdbId("tt0111161");
        movie.setTitle("The Shawshank Redemption");
        movie.setReleaseDate("1994-09-23");
        movie.setGenres(Arrays.asList("Drama", "Crime"));

        review1 = new Review();
        review1.setBody("Great movie!");
        review1.setMovie(movie);

        review2 = new Review();
        review2.setBody("Amazing storytelling!");
        review2.setMovie(movie);
    }

    @Test
    void findByMovieImdbId_ShouldReturnReviewsForMovie() {
        // Given
        Movie savedMovie = entityManager.persistAndFlush(movie);
        review1.setMovie(savedMovie);
        review2.setMovie(savedMovie);
        entityManager.persistAndFlush(review1);
        entityManager.persistAndFlush(review2);

        // When
        List<Review> reviews = reviewRepository.findByMovieImdbId("tt0111161");

        // Then
        assertThat(reviews).hasSize(2);
        assertThat(reviews).extracting(Review::getBody)
                .containsExactlyInAnyOrder("Great movie!", "Amazing storytelling!");
    }

    @Test
    void findByMovieImdbId_WithNoReviews_ShouldReturnEmptyList() {
        // When
        List<Review> reviews = reviewRepository.findByMovieImdbId("tt0000000");

        // Then
        assertThat(reviews).isEmpty();
    }

    @Test
    void save_ShouldPersistReview() {
        // Given
        Movie savedMovie = entityManager.persistAndFlush(movie);
        review1.setMovie(savedMovie);

        // When
        Review savedReview = reviewRepository.save(review1);

        // Then
        assertThat(savedReview.getId()).isNotNull();
        assertThat(savedReview.getBody()).isEqualTo("Great movie!");
        assertThat(savedReview.getMovie().getImdbId()).isEqualTo("tt0111161");

        Review foundReview = entityManager.find(Review.class, savedReview.getId());
        assertThat(foundReview).isNotNull();
        assertThat(foundReview.getBody()).isEqualTo("Great movie!");
    }

    @Test
    void findAll_ShouldReturnAllReviews() {
        // Given
        Movie savedMovie = entityManager.persistAndFlush(movie);
        review1.setMovie(savedMovie);
        review2.setMovie(savedMovie);
        entityManager.persistAndFlush(review1);
        entityManager.persistAndFlush(review2);

        // When
        List<Review> reviews = reviewRepository.findAll();

        // Then
        assertThat(reviews).hasSize(2);
        assertThat(reviews).extracting(Review::getBody)
                .containsExactlyInAnyOrder("Great movie!", "Amazing storytelling!");
    }
}