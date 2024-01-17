package com.pivovarit.movies;

import com.pivovarit.movies.InMemoryMovieRepository;
import com.pivovarit.movies.MovieFacade;
import com.pivovarit.movies.api.MovieAddRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void should_find_all_movies() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "REGULAR");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    void should_find_movie_by_title() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "REGULAR");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findByTitle(movie2.title());
        assertThat(result).isPresent();
        assertThat(result.get().id()).isEqualTo(movie2.id());
        assertThat(result.get().title()).isEqualTo(movie2.title());
        assertThat(result.get().type()).isEqualTo(movie2.type());
    }

    @Test
    void should_find_movie_by_type() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "NEW");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findByType(movie3.type());
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().id()).isEqualTo(movie3.id());
        assertThat(result.iterator().next().title()).isEqualTo(movie3.title());
        assertThat(result.iterator().next().type()).isEqualTo(movie3.type());
    }

    @Test
    void should_find_movie_by_id() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "NEW");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findById(movie2.id());
        assertThat(result.id()).isEqualTo(movie2.id());
        assertThat(result.title()).isEqualTo(movie2.title());
        assertThat(result.type()).isEqualTo(movie2.type());
    }

    @Test
    void should_not_find_movie_by_id() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "NEW");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        assertThatThrownBy(() -> movieService.findById(999))
          .isInstanceOf(RuntimeException.class)
          .hasMessage("Movie not found");
    }

    @Test
    void should_not_find_movie_by_title() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "NEW");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findByTitle("The Godfather 2");
        assertThat(result).isEmpty();
    }

    @Test
    void should_not_find_movie_by_type() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Godfather", "REGULAR");
        var movie3 = new MovieAddRequest(44, "The Dark Knight", "NEW");

        // when
        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);

        // then
        var result = movieService.findByType("OLD");
        assertThat(result).isEmpty();
    }

    @Test
    void should_not_find_all_movies() {
        // given
        var movieService = inMemoryInstance();

        // then
        var result = movieService.findAll();
        assertThat(result).isEmpty();
    }

    @Test
    void should_not_add_movie_with_existing_id() {
        // given
        var movieService = inMemoryInstance();
        var movie = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");

        // when
        movieService.save(movie);
        movieService.save(movie);

        // then
        var result = movieService.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void should_not_add_movie_with_existing_title() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");

        // when
        movieService.save(movie1);
        movieService.save(movie2);

        // then
        var result = movieService.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    @Disabled
    void should_not_add_movie_with_existing_title_and_different_id() {
        // given
        var movieService = inMemoryInstance();
        var movie1 = new MovieAddRequest(42, "The Shawshank Redemption", "REGULAR");
        var movie2 = new MovieAddRequest(43, "The Shawshank Redemption", "REGULAR");

        // when
        movieService.save(movie1);
        movieService.save(movie2);

        // then
        var result = movieService.findAll();
        assertThat(result).hasSize(1);
    }

    public static MovieFacade inMemoryInstance() {
        InMemoryRentalRepository rentals = new InMemoryRentalRepository();
        return new MovieFacade(new InMemoryMovieRepository(), movieId -> Optional.empty(), rentals, new RentalProjections(rentals), event -> {});
    }
}
