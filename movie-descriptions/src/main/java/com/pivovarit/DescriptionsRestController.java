package com.pivovarit;

import com.pivovarit.descriptions.MovieDescriptionsFacade;
import com.pivovarit.descriptions.api.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DescriptionsRestController {

    private final MovieDescriptionsFacade descriptions;

    DescriptionsRestController(MovieDescriptionsFacade descriptions) {
        this.descriptions = descriptions;
    }

    @GetMapping("/descriptions/{id}")
    Description findOneById(@PathVariable int id) {
        return descriptions.findOneById(id).orElseThrow();
    }
}
