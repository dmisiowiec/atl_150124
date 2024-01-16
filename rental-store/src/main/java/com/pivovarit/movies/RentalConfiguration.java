package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, DescriptionsRepository movieDescriptions) {
        return new MovieFacade(movieRepository, movieDescriptions);
    }

    @Bean
    DescriptionsRepository descriptionsRepositoryAdapter(MovieDescriptionsFacade movieDescriptionsFacade) {
        return movieDescriptionsFacade::findOneById;
    }
}
