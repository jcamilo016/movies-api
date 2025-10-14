package dev.jcacosta.movies.services;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.domains.Review;
import dev.jcacosta.movies.repositories.MovieRepository;
import dev.jcacosta.movies.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Review createReview(String reviewBody, String imdbId) {
        Optional<Movie> movieOpt = movieRepository.findByImdbId(imdbId);

        if (movieOpt.isEmpty()) {
            throw new RuntimeException("Movie not found with imdbId: " + imdbId);
        }

        Movie movie = movieOpt.get();
        Review review = new Review(reviewBody, movie);

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByMovieImdbId(String imdbId) {
        return reviewRepository.findByMovieImdbId(imdbId);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
}
