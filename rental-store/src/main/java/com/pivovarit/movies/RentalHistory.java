package com.pivovarit.movies;

import java.util.List;

interface RentalHistory {
    void save(RentalEvent event);

    List<MovieRentalEvent> findAll();

    List<MovieRentalEvent> findAllBy(long accountId);

    List<MovieRentalEvent> unprocessed();

    void markProcessed(MovieRentalEvent event);
}
