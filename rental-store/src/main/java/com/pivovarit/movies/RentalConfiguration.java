package com.pivovarit.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, DescriptionsRepository movieDescriptions, RentalHistory rentalHistory, RentalProjections rentalProjections) {
        return new MovieFacade(movieRepository, movieDescriptions, rentalHistory, rentalProjections);
    }

    @Bean
    RentalProjections rentalProjections(RentalHistory history) {
        return new RentalProjections(history);
    }
}
