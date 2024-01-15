package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Collection;
import java.util.Optional;

class JdbiMovieRepository implements MovieRepository {

    private final Jdbi jdbi;

    JdbiMovieRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public MovieId save(Movie movie) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate("INSERT INTO movies (id, title, type) VALUES (:id, :title, :type)")
              .bind("id", movie.getId().getId())
              .bind("title", movie.getTitle())
              .bind("type", movie.getType().name())
              .execute();
            return movie.getId();
        });
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM movies")
          .map(toMovie()).list());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM movies WHERE title = :title")
          .bind("title", title)
          .map(toMovie())
          .findOne());
    }

    @Override
    public Collection<Movie> findByType(MovieType type) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM movies WHERE type = :type")
          .bind("type", type.toString())
          .map(toMovie()).list());
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM movies WHERE id = :id")
          .bind("id", id.getId())
          .map(toMovie())
          .findOne());
    }

    private static RowMapper<Movie> toMovie() {
        return (rs, ctx) -> new Movie(
          new MovieId(rs.getLong("id")),
          rs.getString("title"),
          MovieType.valueOf(rs.getString("type")));
    }
}
