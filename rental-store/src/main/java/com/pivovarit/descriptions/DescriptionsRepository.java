package com.pivovarit.descriptions;


import java.util.Optional;

interface DescriptionsRepository {
    Optional<RestMovieDescriptionsRepository.Description> findOneById(int movieId);
}
