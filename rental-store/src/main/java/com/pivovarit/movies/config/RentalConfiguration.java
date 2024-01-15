package com.pivovarit.movies.config;

import com.pivovarit.movies.account.AccountRepository;
import com.pivovarit.movies.repository.MovieRepository;
import com.pivovarit.movies.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    MovieService movieService(MovieRepository movieRepository, AccountRepository accountRepository) {
        return new MovieService(movieRepository, accountRepository);
    }

    @Bean
    MovieRepository movieRepository() {
        return new MovieRepository();
    }

    @Bean
    AccountRepository accountRepository() {
        return id -> "foo";
    }
}
