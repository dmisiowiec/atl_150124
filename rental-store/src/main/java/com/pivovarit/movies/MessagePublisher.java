package com.pivovarit.movies;

interface MessagePublisher {
    void send(RentalEvent event);
}
