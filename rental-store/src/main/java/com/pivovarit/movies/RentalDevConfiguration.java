package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
@Profile("dev")
class RentalDevConfiguration {

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
    MovieRepository inMemoryMovieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    DescriptionsRepository descriptionsRepository() {
        return movieId -> Optional.of(new RestMovieDescriptionsRepository.Description("lorem ipsum"));
    }
}
