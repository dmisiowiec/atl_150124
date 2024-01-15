package com.pivovarit.movies;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import com.pivovarit.movies.api.MovieAddRequest;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class MovieUnitTest {

    @RepeatedTest(1000)
    void should_add_movie() {
        // given
        var movieService = inMemoryInstance();
        var movie = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");

        // when
        movieService.save(movie);

        // then
        var result = movieService.findById(movie.getId());
        assertThat(result.getId()).isEqualTo(movie.getId());
        assertThat(result.getTitle()).isEqualTo(movie.getTitle());
        assertThat(result.getType()).isEqualTo(movie.getType());
    }

    public static MovieFacade inMemoryInstance() {
        return new MovieFacade(new InMemoryMovieRepository(), new MovieDescriptionsFacade());
    }
}
