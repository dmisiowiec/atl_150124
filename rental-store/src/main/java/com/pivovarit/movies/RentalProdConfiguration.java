package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
class RentalProdConfiguration {

    @Bean
    Jdbi jdbi(DataSource ds) {
        Jdbi jdbi = Jdbi.create(new TransactionAwareDataSourceProxy(ds));
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    MovieRepository jdbiMovieRepository(Jdbi jdbi) {
        return new JdbiMovieRepository(jdbi);
    }

    @Bean
    DescriptionsRepository descriptionsRepositoryAdapter(@Value("${service.descriptions.url}") String url) {
        return new RestMovieDescriptionsRepository(url);
    }

    @Bean
    RentalHistory jdbiRentalHistory(Jdbi jdbi) {
        return new JdbiRentalHistory(jdbi);
    }
}
