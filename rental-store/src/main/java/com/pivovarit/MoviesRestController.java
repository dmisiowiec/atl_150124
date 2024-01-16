package com.pivovarit;

import com.pivovarit.movies.MovieFacade;
import com.pivovarit.movies.api.MovieDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesRestController {

    private final MovieFacade movieService;

    MoviesRestController(MovieFacade movieFacade) {
        this.movieService = movieFacade;
    }

    @GetMapping("/movies/{id}")
    MovieDto getMovieById(@PathVariable int id) {
        return movieService.findById(id);
    }
}
