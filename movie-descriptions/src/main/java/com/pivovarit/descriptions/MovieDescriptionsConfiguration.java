package com.pivovarit.descriptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MovieDescriptionsConfiguration {

    @Bean
    MovieDescriptionsFacade movieDescriptionsFacade() {
        return new MovieDescriptionsFacade();
    }
}
