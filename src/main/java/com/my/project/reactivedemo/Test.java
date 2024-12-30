package com.my.project.reactivedemo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> nums = Flux.fromIterable(List.of(1, 2, 3, 4, 5));
        nums.log().subscribeOn(Schedulers.boundedElastic())
                .doOnCancel(() -> System.out.println("canceled"))
                .doOnComplete(() -> System.out.println("stream completed"))
                .doOnNext(integer -> System.out.println("next : "+integer))
                .subscribe(x -> System.out.println(x));

        nums.flatMap(integer -> Flux.fromIterable(List.of(6,7,8)))
                .subscribe(System.out::println);
        Thread.sleep(2000);
        System.out.println("End Processing");



    }

    public Mono<String>upperCase(String s){
        return Mono.just(s.toUpperCase());

    }
}
