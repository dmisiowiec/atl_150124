package com.pivovarit.descriptions;

import com.pivovarit.descriptions.api.Description;

import java.util.Optional;

public class MovieDescriptionsFacade {

    public Optional<Description> findOneById(int movieId) {
        return switch (movieId) {
            case 43 ->
              Optional.of(new Description("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
            case 44 ->
              Optional.of(new Description("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."));
            case 45 ->
              Optional.of(new Description("The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate."));
            case 46 ->
              Optional.of(new Description("The former Fellowship members prepare for the final battle. While Frodo and Sam approach Mount Doom to destroy the One Ring, they follow Gollum, unaware of the path he is leading them to."));
            default -> Optional.empty();
        };
    }
}
