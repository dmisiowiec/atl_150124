package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieFacade movieService(MovieRepository movieRepository, MovieDescriptionsFacade movieDescriptionsFacade) {
        return new MovieFacade(movieRepository, movieDescriptionsFacade);
    }

    @Bean
    MovieRepository movieRepository() {
        return new InMemoryMovieRepository();
    }
}
