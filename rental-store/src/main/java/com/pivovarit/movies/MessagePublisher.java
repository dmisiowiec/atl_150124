package com.pivovarit.movies;

interface MessagePublisher {
    void send(MovieRentalEvent event);
}
