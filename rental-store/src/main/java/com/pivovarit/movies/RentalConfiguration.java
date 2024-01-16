package com.pivovarit.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, DescriptionsRepository movieDescriptions) {
        return new MovieFacade(movieRepository, movieDescriptions);
    }
}
