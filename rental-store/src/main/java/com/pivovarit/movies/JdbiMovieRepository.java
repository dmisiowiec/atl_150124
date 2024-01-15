package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

class JdbiMovieRepository implements MovieRepository {

    private final Jdbi jdbi;

    JdbiMovieRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public MovieId save(Movie movie) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO movies (id, title, type) VALUES (:id, :title, :type)")
              .bind("id", "") // TODO
              .bind("title", movie.getTitle())
              .bind("type", movie.getType().name())
              .execute();
        });
        return null;
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbi.withHandle(handle -> {
            List<Movie> list = handle.createQuery("SELECT * FROM movies")
              .map(new RowMapper<Movie>() {
                  @Override
                  public Movie map(ResultSet rs, StatementContext ctx) throws SQLException {
                      return null; // TODO
                  }
              }).list();
            return list;
        });
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Collection<Movie> findByType(MovieType type) {
        return null;
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.empty();
    }
}
