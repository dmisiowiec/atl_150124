package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, MovieDescriptionsFacade movieDescriptionsFacade) {
        return new MovieFacade(movieRepository, movieDescriptionsFacade);
    }
}
