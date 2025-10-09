package dev.jcacosta.movies.controllers;

import dev.jcacosta.movies.domains.Movie;
import dev.jcacosta.movies.services.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable("id") String imdbId){
        return new ResponseEntity<>(movieService.getMovieById(imdbId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Movie>> updateMovie(@PathVariable("id") String imdbId, @RequestBody Movie movieDetails) {
        Optional<Movie> updatedMovie = movieService.updateMovie(imdbId, movieDetails);
        if (updatedMovie.isPresent()) {
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
