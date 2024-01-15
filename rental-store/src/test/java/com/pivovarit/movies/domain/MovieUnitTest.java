package com.pivovarit.movies.domain;

import com.pivovarit.movies.repository.InMemoryMovieRepository;
import com.pivovarit.movies.service.MovieService;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class MovieUnitTest {

    @RepeatedTest(1000)
    void should_add_movie() {
        // given
        MovieService movieService = inMemoryInstance();
        Movie movie = new Movie(new MovieId(42), "The Shawshank Redemption", MovieType.REGULAR);

        // when
        movieService.save(movie);

        // then
        Movie result = movieService.findById(movie.getId().getId());
        assertThat(result).isEqualTo(movie);
    }

    public static MovieService inMemoryInstance() {
        return new MovieService(new InMemoryMovieRepository());
    }
}
