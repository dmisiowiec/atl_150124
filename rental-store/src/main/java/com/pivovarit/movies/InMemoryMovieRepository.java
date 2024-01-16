package com.pivovarit.movies;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> movies = new HashMap<>();

    @Override
    public MovieId save(Movie movie) {
        movies.put(movie.id(), movie);
        return movie.id();
    }

    @Override
    public Collection<Movie> findAll() {
        return movies.values();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream()
          .filter(m -> m.title().equals(title))
          .findAny();
    }

    @Override
    public Collection<Movie> findByType(MovieType type) {
        return movies.values().stream()
          .filter(m -> m.type() == type)
          .toList();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }
}
