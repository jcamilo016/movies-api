package dev.jcacosta.movies.services;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String imdbId){
        return movieRepository.findByImdbId(imdbId);
    }

    public Optional<Movie> updateMovie(String imdbId, Movie movieDetails) {
        Optional<Movie> existingMovie = movieRepository.findByImdbId(imdbId);
        if (existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            movie.setTitle(movieDetails.getTitle());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setTrailerLink(movieDetails.getTrailerLink());
            movie.setPoster(movieDetails.getPoster());
            movie.setGenres(movieDetails.getGenres());
            movie.setBackdrops(movieDetails.getBackdrops());
            return Optional.of(movieRepository.save(movie));
        }
        return Optional.empty();
    }
}
