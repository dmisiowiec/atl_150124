package com.pivovarit.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
@Profile("dev")
class RentalDevConfiguration {

    @Bean
    MovieRepository inMemoryMovieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    DescriptionsRepository descriptionsRepository() {
        return movieId -> Optional.of(new RestMovieDescriptionsRepository.Description("lorem ipsum"));
    }
}
