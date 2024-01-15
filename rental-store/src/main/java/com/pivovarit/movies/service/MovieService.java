package com.pivovarit.movies.service;

import com.pivovarit.movies.account.AccountRepository;
import com.pivovarit.movies.domain.Movie;
import com.pivovarit.movies.domain.MovieId;
import com.pivovarit.movies.repository.MovieRepository;
import org.springframework.stereotype.Component;

public class MovieService {

    private final MovieRepository movieRepository;
    private final AccountRepository accountRepository;

    public MovieService(MovieRepository movieRepository, AccountRepository accountRepository) {
        this.movieRepository = movieRepository;
        this.accountRepository = accountRepository;
    }

    public Movie findById(int id) {
        return movieRepository.findById(new MovieId(id))
          .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
