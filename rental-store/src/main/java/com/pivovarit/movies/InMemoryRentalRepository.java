package com.pivovarit.movies;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InMemoryRentalRepository implements RentalHistory {

    private final List<MovieRentalEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public synchronized void save(RentalEvent event) {
        events.add(new MovieRentalEvent(events.size(), Instant.now().toString(), event.type(), event.movieId(), event.accountId()));
    }

    @Override
    public List<MovieRentalEvent> findAll() {
        return List.copyOf(events);
    }

    @Override
    public List<MovieRentalEvent> findAllBy(long accountId) {
        return events.stream()
          .filter(e -> e.accountId() == accountId)
          .toList();
    }

    @Override
    public List<MovieRentalEvent> unprocessed() {
        return List.of();
    }

    @Override
    public void markProcessed(MovieRentalEvent event) {

    }
}
