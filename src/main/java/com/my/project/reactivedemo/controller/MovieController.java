package com.my.project.reactivedemo.controller;

import com.my.project.reactivedemo.domain.dto.Movie;
import com.my.project.reactivedemo.exception.MovieNotFoundException;
import com.my.project.reactivedemo.exception.NoSuchElementFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @GetMapping("/{name}")
    public ResponseEntity<Flux<Movie>> getMovies(@PathVariable("name") String name) {
        return ResponseEntity.ok(Flux.just(Movie.builder().id(2L).name(name).build()));

    }

    @PostMapping("/{name}")
    public ResponseEntity<Flux<Movie>> updateMovies(@RequestBody Movie movie) throws NoSuchElementFoundException {
        if (movie.getId() == 3) {
            throw new MovieNotFoundException(movie.getId());
        }
        if (movie.getId() == 2) {
            return ResponseEntity.ok(Flux.just(Movie.builder().id(movie.getId()).name(movie.getName()).build()));
        }
        throw new NoSuchElementFoundException("No movie found");
    }

    @GetMapping(value = "/fetch-all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Movie>> postMovies() {
        Movie movie1 = new Movie("a", 1L);
        Movie movie2 = new Movie("b", 2L);
        Movie movie3 = new Movie("c", 3L);
        Movie movie4 = new Movie("d", 4l);
        Movie movie5 = new Movie("e", 5l);
        Flux<Movie> movies = Flux.just(movie1, movie2, movie3, movie4, movie5).delayElements(Duration.ofSeconds(1)).log();
        return ResponseEntity.ok(movies);
    }
}
