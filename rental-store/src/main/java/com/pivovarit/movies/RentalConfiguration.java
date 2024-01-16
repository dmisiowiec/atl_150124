package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    ApplicationRunner moviePopulator(MovieFacade movie) {
        return args -> {
            movie.save(new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR"));
            movie.save(new MovieAddRequest(43, "The Godfather", "REGULAR"));
            movie.save(new MovieAddRequest(44, "The Dark Knight", "REGULAR"));
            movie.save(new MovieAddRequest(45, "The Godfather: Part II", "REGULAR"));
            movie.save(new MovieAddRequest(46, "The Lord of the Rings: The Return of the King", "NEW"));
        };
    }

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, DescriptionsRepository movieDescriptions) {
        return new MovieFacade(movieRepository, movieDescriptions);
    }
}
