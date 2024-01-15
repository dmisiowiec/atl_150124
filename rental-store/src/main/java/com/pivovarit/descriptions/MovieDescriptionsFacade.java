package com.pivovarit.descriptions;

import com.pivovarit.descriptions.api.Description;

import java.util.Optional;

public class MovieDescriptionsFacade {

    public Optional<Description> findOneById(int movieId) {
        return switch (movieId) {
            case 1 -> Optional.of(new Description("Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time."));
            case 2 -> Optional.of(new Description("A cynical American expatriate struggles to decide whether or not he should help his former lover and her fugitive husband escape French Morocco."));
            case 3 -> Optional.of(new Description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity."));
            default -> Optional.empty();
        };
    }
}
