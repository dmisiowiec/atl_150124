package com.pivovarit.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
class RentalDevConfiguration {

    @Bean
    MovieRepository inMemoryMovieRepository() {
        return new InMemoryMovieRepository();
    }
}
