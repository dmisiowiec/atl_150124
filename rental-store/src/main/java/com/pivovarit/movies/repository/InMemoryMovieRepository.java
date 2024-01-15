package com.pivovarit.movies.repository;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> movies = new HashMap<>();

    @Override
    public MovieId save(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return movies.values();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream()
          .filter(m -> m.getTitle().equals(title))
          .findAny();
    }

    @Override
    public Collection<Movie> findByType(MovieType type) {
        return movies.values().stream()
          .filter(m -> m.getType() == type)
          .toList();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }
}
