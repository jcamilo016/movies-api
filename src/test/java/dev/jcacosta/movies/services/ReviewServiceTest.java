package dev.jcacosta.movies.services;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.domains.Review;
import dev.jcacosta.movies.repositories.MovieRepository;
import dev.jcacosta.movies.repositories.ReviewRepository;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private ReviewService reviewService;

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
    void createReview_WithValidMovieId_ShouldCreateReview() {
        // Given
        String reviewBody = "Excellent movie!";
        String imdbId = "tt0111161";
        Review savedReview = new Review(reviewBody, movie);
        savedReview.setId(3L);

        when(movieRepository.findByImdbId(imdbId)).thenReturn(Optional.of(movie));
        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);

        // When
        Review actualReview = reviewService.createReview(reviewBody, imdbId);

        // Then
        assertThat(actualReview).isNotNull();
        assertThat(actualReview.getBody()).isEqualTo(reviewBody);
        assertThat(actualReview.getMovie()).isEqualTo(movie);
        assertThat(actualReview.getId()).isEqualTo(3L);
    }

    @Test
    void createReview_WithInvalidMovieId_ShouldThrowException() {
        // Given
        String reviewBody = "Good movie!";
        String invalidImdbId = "invalid";

        when(movieRepository.findByImdbId(invalidImdbId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reviewService.createReview(reviewBody, invalidImdbId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Movie not found with imdbId: invalid");
    }

    @Test
    void getReviewsByMovieImdbId_ShouldReturnReviews() {
        // Given
        String imdbId = "tt0111161";
        List<Review> expectedReviews = Arrays.asList(review1, review2);

        when(reviewRepository.findByMovieImdbId(imdbId)).thenReturn(expectedReviews);

        // When
        List<Review> actualReviews = reviewService.getReviewsByMovieImdbId(imdbId);

        // Then
        assertThat(actualReviews).hasSize(2);
        assertThat(actualReviews).containsExactly(review1, review2);
    }

    @Test
    void getReviewsByMovieImdbId_WithNoReviews_ShouldReturnEmptyList() {
        // Given
        String imdbId = "tt0000000";

        when(reviewRepository.findByMovieImdbId(imdbId)).thenReturn(Arrays.asList());

        // When
        List<Review> actualReviews = reviewService.getReviewsByMovieImdbId(imdbId);

        // Then
        assertThat(actualReviews).isEmpty();
    }

    @Test
    void getAllReviews_ShouldReturnAllReviews() {
        // Given
        List<Review> expectedReviews = Arrays.asList(review1, review2);

        when(reviewRepository.findAll()).thenReturn(expectedReviews);

        // When
        List<Review> actualReviews = reviewService.getAllReviews();

        // Then
        assertThat(actualReviews).hasSize(2);
        assertThat(actualReviews).containsExactly(review1, review2);
    }

    @Test
    void getReviewById_WithExistingId_ShouldReturnReview() {
        // Given
        Long reviewId = 1L;

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review1));

        // When
        Optional<Review> actualReview = reviewService.getReviewById(reviewId);

        // Then
        assertThat(actualReview).isPresent();
        assertThat(actualReview.get()).isEqualTo(review1);
    }

    @Test
    void getReviewById_WithNonExistingId_ShouldReturnEmpty() {
        // Given
        Long nonExistingId = 999L;

        when(reviewRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When
        Optional<Review> actualReview = reviewService.getReviewById(nonExistingId);

        // Then
        assertThat(actualReview).isEmpty();
    }
}