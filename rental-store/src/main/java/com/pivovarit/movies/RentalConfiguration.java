package com.pivovarit.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    RentalHistory inMemoryRentalHistory() {
        return new InMemoryRentalRepository();
    }

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, DescriptionsRepository movieDescriptions, RentalHistory rentalHistory) {
        return new MovieFacade(movieRepository, movieDescriptions, rentalHistory);
    }
}
