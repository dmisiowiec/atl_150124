package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;

class MovieConverter {

    static MovieDto from(Movie movie) {
        return new MovieDto(movie.getId().getId(), movie.getTitle(), movie.getType().toString());
    }

    static Movie from(MovieAddRequest movie) {
        return new Movie(new MovieId(movie.getId()), movie.getTitle(), MovieType.valueOf(movie.getType()));
    }
}
