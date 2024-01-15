package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled // TODO
@SpringBootTest
@ActiveProfiles("prod")
class MovieIntegrationTest {

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private Jdbi jdbi;

    @AfterEach
    void setUp() {
        jdbi.useHandle(handle -> handle.execute("DELETE FROM movies"));
    }

    @RepeatedTest(10)
    public void shouldSaveMovie() throws Exception {
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
