package com.pivovarit.movies;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.api.MovieDto;

class MovieConverter {

    static MovieDto from(Movie movie) {
        return new MovieDto(movie.id().id(), movie.title(), movie.type().toString());
    }

    static Movie from(MovieAddRequest movie) {
        return new Movie(new MovieId(movie.id()), movie.title(), MovieType.valueOf(movie.type()));
    }
}
