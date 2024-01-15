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
        var result = movieService.findById(movie.id());
        assertThat(result.id()).isEqualTo(movie.id());
        assertThat(result.title()).isEqualTo(movie.title());
        assertThat(result.type()).isEqualTo(movie.type());
    }

    public static MovieFacade inMemoryInstance() {
        return new MovieFacade(new InMemoryMovieRepository(), new MovieDescriptionsFacade());
    }
}
