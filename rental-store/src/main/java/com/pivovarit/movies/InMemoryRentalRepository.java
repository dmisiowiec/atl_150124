package com.pivovarit.movies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InMemoryRentalRepository implements RentalHistory {

    private final List<RentalEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(RentalEvent event) {
        events.add(event);
    }

    @Override
    public List<RentalEvent> findAll() {
        return List.copyOf(events);
    }

    @Override
    public List<RentalEvent> findAllBy(long accountId) {
        return events.stream()
          .filter(e -> e.accountId() == accountId)
          .toList();
    }
}
