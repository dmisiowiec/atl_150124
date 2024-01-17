package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;

record JdbiRentalHistory(Jdbi jdbi) implements RentalHistory {

    @Override
    public void save(RentalEvent event) {
        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO rental_history (event_type, account_id, movie_id) VALUES (:event_type, :account_id, :movie_id)")
          .bind("event_type", event.type().toString())
          .bind("account_id", event.accountId())
          .bind("movie_id", event.movieId().id())
          .execute());
    }

    @Override
    public List<MovieRentalEvent> findAll() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM rental_history ORDER BY id")
          .map(toRentalEvent())
          .list());
    }

    @Override
    public List<MovieRentalEvent> findAllBy(long accountId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM rental_history WHERE account_id = :account_id ORDER BY id ")
          .bind("account_id", accountId)
          .map(toRentalEvent())
          .list());
    }

    @Override
    public List<MovieRentalEvent> unprocessed() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM rental_history WHERE processed = false ORDER BY id")
          .map(toRentalEvent())
          .list());
    }

    @Override
    public void markProcessed(MovieRentalEvent event) {
        jdbi.withHandle(handle -> handle.createUpdate("UPDATE rental_history SET processed = true WHERE id = :id")
          .bind("id", event.id())
          .execute());
    }

    private static RowMapper<MovieRentalEvent> toRentalEvent() {
        return (rs, ctx) -> {
            var id = rs.getLong("id");
            var timestamp = rs.getString("timestamp");
            var type = RentalEvent.EventType.valueOf(rs.getString("event_type"));
            var accountId = rs.getLong("account_id");
            var movieId = new MovieId(rs.getLong("movie_id"));
            return new MovieRentalEvent(id, timestamp, type, movieId, accountId);
        };
    }
}
