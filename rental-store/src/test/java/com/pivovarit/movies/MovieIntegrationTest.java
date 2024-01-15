package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovieIntegrationTest {

    @Autowired
    private MovieFacade movieFacade;

    @Test
    void shouldSaveMovie() throws Exception {
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
