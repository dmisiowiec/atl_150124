package com.pivovarit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 1. Hexagonal w MovieService: MovieRepository jako interfejs
// 2. Implementacja MovieRepository na HashMap


// http://github.com/4comprehension/atl_150124
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
