package dev.jcacosta.movies.repositories;

import dev.jcacosta.movies.domains.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieImdbId(String imdbId);
}
