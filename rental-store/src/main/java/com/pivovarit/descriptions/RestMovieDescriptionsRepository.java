package com.pivovarit.descriptions;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

record RestMovieDescriptionsRepository(String url) implements DescriptionsRepository {

    @Override
    public Optional<Description> findOneById(int movieId) {
        var client = RestClient.builder()
          .baseUrl(url)
          .build();

        try {
            return Optional.ofNullable(client.get()
              .uri("/descriptions/{id}", movieId)
              .retrieve()
              .body(Description.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

    record Description(String description) {
    }
}
