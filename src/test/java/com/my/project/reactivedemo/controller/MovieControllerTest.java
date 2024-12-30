package com.my.project.reactivedemo.controller;

import com.my.project.reactivedemo.domain.dto.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest(controllers = MovieController.class)
@AutoConfigureWebTestClient
class MovieControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void testMoviesGet() {
        Flux<Movie> movies = webTestClient.get().uri("/movies/Batman")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Movie.class)
                .getResponseBody();
        StepVerifier.create(movies)
                .expectNext(Movie.builder().id(2L).name("Batman").build())
                .verifyComplete();
    }

    @Test
    void testMoviesGet1() {
        webTestClient.get().uri("/movies/Batman")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Movie.class)
                .consumeWith(movie -> {
                    Movie actual = movie.getResponseBody().get(0);
                    assertEquals(actual.getName(), "Batman");
                    assertEquals(actual.getId(), 2);
                });
    }

    @Test
    void postMovies() {
        Flux<Movie> movies = webTestClient.get().uri("/movies/fetch-all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Movie.class)
                .getResponseBody();
        StepVerifier.create(movies)
                .expectNext(Movie.builder().id(1L).name("a").build())
                .thenCancel();
    }
}