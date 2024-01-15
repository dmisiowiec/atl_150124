package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
class RentalConfiguration {

    @Bean
    Jdbi jdbi(DataSource ds) {
        return Jdbi.create(ds);
    }

    @Bean
    MovieFacade movieService(MovieRepository movieRepository, MovieDescriptionsFacade movieDescriptionsFacade) {
        return new MovieFacade(movieRepository, movieDescriptionsFacade);
    }

    @Bean
    @Primary
    MovieRepository jdbiMovieRepository(Jdbi jdbi) {
        return new JdbiMovieRepository(jdbi);
    }

    @Bean
    MovieRepository movieRepository() {
        return new InMemoryMovieRepository();
    }
}
