package com.pivovarit.movies;

import com.pivovarit.descriptions.api.Description;

import java.util.Optional;

interface DescriptionsRepository {
    Optional<Description> findOneById(int movieId);
}
