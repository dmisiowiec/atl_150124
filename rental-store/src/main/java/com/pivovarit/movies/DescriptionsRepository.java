package com.pivovarit.movies;


import java.util.Optional;

interface DescriptionsRepository {
    Optional<RestMovieDescriptionsRepository.Description> findOneById(int movieId);
}
