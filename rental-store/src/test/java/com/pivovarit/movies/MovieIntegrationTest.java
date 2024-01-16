package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
@Testcontainers
class MovieIntegrationTest {

    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:16.1"))
      .withUsername("postgres")
      .withPassword("mysecretpassword");

    @DynamicPropertySource
    public static void lateinit(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private Jdbi jdbi;

    @AfterEach
    void setUp() {
        jdbi.useHandle(handle -> handle.execute("DELETE FROM movies"));
    }

    @Test
    public void shouldSaveMovie() {
        // given
        var movie = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");

        // when
        movieFacade.save(movie);

        // then
        var result = movieFacade.findById(movie.id());
        assertThat(result.id()).isEqualTo(movie.id());
        assertThat(result.title()).isEqualTo(movie.title());
        assertThat(result.type()).isEqualTo(movie.type());
    }
}
