package com.pivovarit.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// DI -> MovieRepository -> MovieService
// wystawić proste API REST do MovieService#findById
// http://github.com/4comprehension/atl_150124
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
