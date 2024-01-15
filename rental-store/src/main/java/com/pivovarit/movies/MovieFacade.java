package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public class MovieFacade {

    private final MovieRepository movieRepository;
    private final MovieDescriptionsFacade movieDescriptionsFacade;

    MovieFacade(MovieRepository movieRepository, MovieDescriptionsFacade movieDescriptionsFacade) {
        this.movieRepository = movieRepository;
        this.movieDescriptionsFacade = movieDescriptionsFacade;
    }

    public MovieDto findById(long id) {
        return movieRepository.findById(new MovieId(id))
          .map(MovieConverter::from)
          .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public long save(MovieAddRequest movie) {
        return movieRepository.save(MovieConverter.from(movie)).getId();
    }

    public Collection<MovieDto> findAll() {
        return movieRepository.findAll().stream().map(MovieConverter::from).toList();
    }

    public Optional<MovieDto> findByTitle(String title) {
        return movieRepository.findByTitle(title).map(MovieConverter::from);
    }

    public Collection<MovieDto> findByType(String type) {
        return movieRepository.findByType(MovieType.valueOf(type)).stream().map(MovieConverter::from).toList();
    }
}
