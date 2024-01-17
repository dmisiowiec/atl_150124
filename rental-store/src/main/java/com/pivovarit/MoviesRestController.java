package com.pivovarit;

import com.pivovarit.movies.MovieFacade;
import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MoviesRestController {

    private final MovieFacade movieService;

    MoviesRestController(MovieFacade movieFacade) {
        this.movieService = movieFacade;
    }

    @PostMapping("/movies")
    void addMovie(MovieAddRequest movie) {
        movieService.save(movie);
    }

    @GetMapping("/movies?type={type}")
    Collection<MovieDto> getMovies(@RequestParam(required = false) Optional<String> type) {
        return type.map(movieService::findByType).orElseGet(movieService::findAll);
    }

    @GetMapping("/movies/{id}")
    MovieDto getMovieById(@PathVariable int id) {
        return movieService.findById(id);
    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<MovieDto> findByTitle(String title) {
        return movieService.findByTitle(title)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
