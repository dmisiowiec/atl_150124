package com.pivovarit.movies;

import java.util.List;

interface RentalHistory {
    void save(RentalEvent event);

    List<RentalEvent> findAll();

    List<RentalEvent> findAllBy(long accountId);
}
