package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
class RentalProdConfiguration {

    @Bean
    Jdbi jdbi(DataSource ds) {
        return Jdbi.create(ds);
    }

    @Bean
    MovieRepository jdbiMovieRepository(Jdbi jdbi) {
        return new JdbiMovieRepository(jdbi);
    }
}
