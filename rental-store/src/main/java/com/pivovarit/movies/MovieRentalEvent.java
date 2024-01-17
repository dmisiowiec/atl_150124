package com.pivovarit.movies;

record MovieRentalEvent(long id, String timestamp, RentalEvent.EventType type, MovieId movieId, long accountId) {
}
