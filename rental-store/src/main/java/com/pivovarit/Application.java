package com.pivovarit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. Dodaj pole "description" do MovieDto
// 2. Pole powinno być uzupelniane przez MovieDescriptionsFacade
// http://github.com/4comprehension/atl_150124
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
