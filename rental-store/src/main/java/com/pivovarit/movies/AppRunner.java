package com.pivovarit.movies;

import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.domain.MovieType;
import com.pivovarit.movies.service.MovieService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class AppRunner implements ApplicationRunner {

    private final MovieService movieService;

    AppRunner(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(movieService.findAll());
        movieService.save(new Movie(new MovieId(1), "The Shawshank Redemption", MovieType.OLD));
        movieService.save(new Movie(new MovieId(2), "The Godfather", MovieType.OLD));
        movieService.save(new Movie(new MovieId(3), "The Dark Knight", MovieType.NEW));

        System.out.println(movieService.findAll());
    }
}
