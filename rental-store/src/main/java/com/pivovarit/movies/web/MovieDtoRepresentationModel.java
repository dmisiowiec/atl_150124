package com.pivovarit.movies.web;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pivovarit.movies.api.MovieDto;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class MovieDtoRepresentationModel extends RepresentationModel<MovieDtoRepresentationModel> {
    private final MovieDto movie;

    MovieDtoRepresentationModel(MovieDto movie) {
        this.movie = movie;
    }

    @JsonUnwrapped
    public MovieDto getMovie() {
        return movie;
    }

    static MovieDtoRepresentationModel from(MovieDto dto) {
        var model = new MovieDtoRepresentationModel(dto);
        model.add(linkTo(methodOn(MoviesRestController.class).getMovieById((int) dto.id())).withSelfRel());
        model.add(linkTo(methodOn(MoviesRestController.class).findByTitle(dto.title())).withRel("movieByTitle"));
        model.add(linkTo(methodOn(MoviesRestController.class).getMovies()).withRel("allMovies"));
        model.add(linkTo(methodOn(MoviesRestController.class).getMoviesByType(dto.type())).withRel("sameTypeMovies"));
        model.add(linkTo(methodOn(MoviesRestController.class).rentMovie((int) dto.id())).withRel("rent"));
        model.add(linkTo(methodOn(MoviesRestController.class).returnMovie((int) dto.id())).withRel("return"));
        return model;
    }
}
