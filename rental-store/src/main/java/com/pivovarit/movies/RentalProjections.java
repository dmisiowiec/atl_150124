package com.pivovarit.movies;

import java.util.stream.Collectors;

record RentalProjections(RentalHistory history) {

    UserRentals userRentals(long accountId) {
        return history.findAllBy(accountId)
          .stream()
          .collect(Collectors.collectingAndThen(Collectors.toList(), s -> new UserRentals(accountId).apply(s)));
    }
}
