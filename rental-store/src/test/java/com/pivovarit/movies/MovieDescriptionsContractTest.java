package com.pivovarit.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
  stubsMode = StubRunnerProperties.StubsMode.LOCAL,
  ids = "com.forc.rental:movie-descriptions:+:stubs:8090"
)
class MovieDescriptionsContractTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestMovieDescriptionsRepository repository;

    @Test
    void shouldRetrieveMovieDescriptions() {
        assertThat(repository.findOneById(43)).isNotEmpty();
    }

    @Test
    void shouldRetrieveMovieNonexistingDescriptions() {
        assertThat(repository.findOneById(1)).isEmpty();
    }

    @TestConfiguration
    static class RestDescriptionsClientConfiguration {

        @Primary
        @Bean
        public RestMovieDescriptionsRepository descriptionsRepository2() {
            return new RestMovieDescriptionsRepository("http://localhost:8090");
        }
    }
}
