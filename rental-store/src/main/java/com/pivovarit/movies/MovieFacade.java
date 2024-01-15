package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import com.pivovarit.descriptions.api.Description;
import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class MovieFacade {

    private final MovieRepository movieRepository;
    private final MovieDescriptionsFacade movieDescriptionsFacade;

    MovieFacade(MovieRepository movieRepository, MovieDescriptionsFacade movieDescriptionsFacade) {
        this.movieRepository = movieRepository;
        this.movieDescriptionsFacade = movieDescriptionsFacade;
    }

    public MovieDto findById(long id) {
        return movieRepository.findById(new MovieId(id))
          .map(toMovieWithDescription())
          .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public long save(MovieAddRequest movie) {
        return movieRepository.save(MovieConverter.from(movie)).id();
    }

    public Collection<MovieDto> findAll() {
        return movieRepository.findAll().stream().map(toMovieWithDescription()).toList();
    }

    public Optional<MovieDto> findByTitle(String title) {
        return movieRepository.findByTitle(title).map(toMovieWithDescription());
    }

    public Collection<MovieDto> findByType(String type) {
        return movieRepository.findByType(MovieType.valueOf(type)).stream().map(toMovieWithDescription()).toList();
    }

    private Function<Movie, MovieDto> toMovieWithDescription() {
        return movie -> {
            var description = movieDescriptionsFacade.findOneById((int) movie.id().id()).orElse(new Description(""));
            return MovieConverter.from(movie, description.description());
        };
    }
}
