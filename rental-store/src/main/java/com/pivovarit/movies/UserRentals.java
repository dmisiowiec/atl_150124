package com.pivovarit.movies;

import java.util.ArrayList;
import java.util.List;

class UserRentals {
    private final long accountId;
    private final List<MovieId> currentlyRented = new ArrayList<>();

    UserRentals(long accountId) {
        this.accountId = accountId;
    }

    public UserRentals apply(MovieRentalEvent event) {
        switch (event.type()) {
            case RENTED -> currentlyRented.add(event.movieId());
            case RETURNED -> currentlyRented.remove(event.movieId());
        }
        return this;
    }

    public UserRentals apply(List<MovieRentalEvent> events) {
        events.forEach(this::apply);
        return this;
    }

    public boolean canRentMovies() {
        return currentlyRented.size() < 3;
    }

    public boolean canRent(MovieId movieId) {
        return !currentlyRented.contains(movieId);
    }

    public boolean canReturn(MovieId movieId) {
        return currentlyRented.contains(movieId);
    }

    @Override
    public String toString() {
        return "UserRentals{accountId=%d, currentlyRented=%s}".formatted(accountId, currentlyRented);
    }
}
