package com.pivovarit.movies.config;

import com.pivovarit.movies.repository.InMemoryMovieRepository;
import com.pivovarit.movies.repository.MovieRepository;
import com.pivovarit.movies.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieService movieService(MovieRepository movieRepository) {
        return new MovieService(movieRepository);
    }

    @Bean
    MovieRepository movieRepository() {
        return new InMemoryMovieRepository();
    }
}
