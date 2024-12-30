package com.my.project.reactivedemo.controller;

import com.my.project.reactivedemo.domain.dto.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(MovieController.class)
@AutoConfigureWebTestClient
class MovieControllerTest1 {
    @Autowired
     WebTestClient webTestClient;
    @Test
    void postMovies() {
     Flux<Movie> movies=  webTestClient.get().uri("/movies/fetch-all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Movie.class)
                .getResponseBody();
        StepVerifier.create(movies)
                .expectNext(Movie.builder().id(1l).name("a").build())
                .thenCancel();


    }
}