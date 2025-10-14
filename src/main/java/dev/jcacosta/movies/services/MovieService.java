package dev.jcacosta.movies.services;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String imdbId){
        return movieRepository.findByImdbId(imdbId);
    }

    public Movie createMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title is required");
        }
        if (movie.getImdbId() == null || movie.getImdbId().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie IMDB ID is required");
        }
        
        Optional<Movie> existingMovie = movieRepository.findByImdbId(movie.getImdbId());
        if (existingMovie.isPresent()) {
            throw new IllegalArgumentException("Movie with IMDB ID " + movie.getImdbId() + " already exists");
        }
        
        return movieRepository.save(movie);
    }
}
