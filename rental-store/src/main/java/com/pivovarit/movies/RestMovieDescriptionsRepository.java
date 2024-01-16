package com.pivovarit.movies;

import com.pivovarit.descriptions.api.Description;

import java.util.Optional;

record RestMovieDescriptionsRepository(String url) implements DescriptionsRepository {

    @Override
    public Optional<Description> findOneById(int movieId) {
        return Optional.empty();
    }
}
