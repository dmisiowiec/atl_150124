package com.pivovarit.movies.service;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;
import com.pivovarit.movies.repository.MovieRepository;

import java.util.Collection;
import java.util.Optional;

public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findById(int id) {
        return movieRepository.findById(new MovieId(id))
          .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public MovieId save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Collection<Movie> findByType(MovieType type) {
        return movieRepository.findByType(type);
    }
}
