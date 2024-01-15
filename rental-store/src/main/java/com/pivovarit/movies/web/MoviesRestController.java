package com.pivovarit.movies.web;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MoviesRestController {

    private final MovieService movieService;

    MoviesRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    Movie getMovieById(@PathVariable int id) {
        return movieService.findById(id);
    }
}
