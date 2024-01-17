package com.pivovarit.movies.web;

import com.pivovarit.movies.MovieFacade;
import com.pivovarit.movies.api.MovieAddRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MoviesRestController {

    private final MovieFacade movieService;

    MoviesRestController(MovieFacade movieFacade) {
        this.movieService = movieFacade;
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "title": "The Matrix", "type": "NEW"}' http://localhost:8081/movies
    // curl -X POST -H "Content-Type: application/json" -d '{"id": 2, "title": "The Godfather", "type": "REGULAR"}' http://localhost:8081/movies
    // curl -X POST -H "Content-Type: application/json" -d '{"id": 3, "title": "The Lord of the Rings", "type": "OLD"}' http://localhost:8081/movies
    // curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "title": "Pulp Fiction", "type": "REGULAR"}' http://localhost:8081/movies
    // curl -X POST -H "Content-Type: application/json" -d '{"id": 5, "title": "The Good, the Bad and the Ugly", "type": "OLD"}' http://localhost:8081/movies
    // curl -X POST -H "Content-Type: application/json" -d '{"id": 6, "title": "The Dark Knight", "type": "NEW"}' http://localhost:8081/movies
    @Operation(description = "Add movie")
    @PostMapping("/movies")
    Map<String, Long> addMovie(@RequestBody MovieAddRequest movie) {
        System.out.println("Adding movie: " + movie);
        movieService.save(movie);
        return Map.of("id", movie.id());
    }

    @Operation(parameters = @Parameter(name = "type", required = false), description = "Get all movies")
    @GetMapping("/movies")
    Collection<MovieDtoRepresentationModel> getMovies() {
        return movieService.findAll().stream().map(MovieDtoRepresentationModel::from).toList();
    }

    @Operation(hidden = true)
    @GetMapping(value = "/movies", params = "type")
    Collection<MovieDtoRepresentationModel> getMoviesByType(@RequestParam String type) {
        if (type != null) {
            return movieService.findByType(type).stream().map(MovieDtoRepresentationModel::from).toList();
        } else {
            return movieService.findAll().stream().map(MovieDtoRepresentationModel::from).toList();
        }
    }

    @Operation(description = "Get movie by id")
    @GetMapping("/movies/{id}")
    MovieDtoRepresentationModel getMovieById(@PathVariable int id) {
        return MovieDtoRepresentationModel.from(movieService.findById(id));
    }

    @PostMapping("/movies/{id}/rent")
    ResponseEntity<Void> rentMovie(@PathVariable int id, @RequestBody Account account) {
        movieService.rentMovie(id, account.accountId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/movies/{id}/return")
    ResponseEntity<Void> returnMovie(@PathVariable int id, @RequestBody Account account) {
        movieService.returnMovie(id, account.accountId());
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Find movie by title")
    @GetMapping("/movies/title/{title}")
    public ResponseEntity<MovieDtoRepresentationModel> findByTitle(@PathVariable String title) {
        return movieService.findByTitle(title)
          .map(MovieDtoRepresentationModel::from)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public record Account(long accountId) {
    }
}
