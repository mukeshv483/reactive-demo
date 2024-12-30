package com.my.project.reactivedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class TestTest {
    com.my.project.reactivedemo.Test test;
    @BeforeEach
    void init(){
        test=new com.my.project.reactivedemo.Test();
    }
    @Test
    void test() {
        Flux<Integer> nums = Flux.just(1, 2, 3);
        StepVerifier.create(nums)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();

    }

    @Test
    void test1() {
        Flux<Integer> nums = Flux.just(1, 2, 3);
        StepVerifier.create(nums)
                .expectNext(1, 2, 3)
                .verifyComplete();

    }
    @Test
    void test3() {
       Mono<String> upperCase=test.upperCase("Mukesh");
       StepVerifier.create(upperCase)
               .expectNext("MUKESH")
               .verifyComplete();
    }


}