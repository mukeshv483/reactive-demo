package com.my.project.reactivedemo.domain.repository;

import com.my.project.reactivedemo.domain.entity.MovieInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;
   List<MovieInfo> movies;
    @BeforeEach
    void setUp() {
        var movieInfo=MovieInfo.builder()
                .movieInfoId("1")
                .name("Batman")
                .year(2024)
                .releaseDate(LocalDate.now())
                .movieInfoId("")
                .build();
        var movieInfo1=MovieInfo.builder()
                .movieInfoId("2")
                .name("superman")
                .year(2024)
                .releaseDate(LocalDate.now())
                .movieInfoId("")
                .build();
        movieInfoRepository.save(movieInfo);
        movieInfoRepository.save(movieInfo1);
        movies=List.of(movieInfo,movieInfo1);
    }

    @Test
    void testFindAll(){
        Mockito.when(movieInfoRepository.findAll()).thenReturn(Flux.fromIterable(movies));
    Flux<MovieInfo> moviesInfo=movieInfoRepository.findAll();
        StepVerifier.create(moviesInfo)
                .expectNextCount(2)
                .verifyComplete();

    }


}