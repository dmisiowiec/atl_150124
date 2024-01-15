package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled // TODO
@SpringBootTest
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
        var result = movieFacade.findById(movie.getId());
        assertThat(result.getId()).isEqualTo(movie.getId());
        assertThat(result.getTitle()).isEqualTo(movie.getTitle());
        assertThat(result.getType()).isEqualTo(movie.getType());
    }
}
