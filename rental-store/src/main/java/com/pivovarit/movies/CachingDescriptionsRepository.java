package com.pivovarit.movies;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class CachingDescriptionsRepository implements DescriptionsRepository {

    private final DescriptionsRepository repository;

    CachingDescriptionsRepository(DescriptionsRepository repository) {
        this.repository = repository;
    }

    private final ConcurrentMap<Integer, RestMovieDescriptionsRepository.Description> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<RestMovieDescriptionsRepository.Description> findOneById(int movieId) {
        var result = cache.get(movieId);
        if (result != null) {
            return Optional.of(result);
        } else {
            var maybeResult = repository.findOneById(movieId);
            System.out.println("found: " + maybeResult + " caching...");
            maybeResult.ifPresent(description -> cache.put(movieId, description));
            return maybeResult;
        }
    }
}
