package com.pivovarit.movies;

import com.pivovarit.movies.Movie;
import com.pivovarit.movies.MovieId;
import com.pivovarit.movies.MovieType;

import java.util.Collection;
import java.util.Optional;

interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    Collection<Movie> findByType(MovieType type);

    Optional<Movie> findById(MovieId id);
}
