package com.pivovarit.movies;

record RentalEvent(EventType type, MovieId movieId, long accountId) {

    enum EventType {
        RENTED, RETURNED
    }
}
