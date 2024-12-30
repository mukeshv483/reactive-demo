package com.my.project.reactivedemo.domain.repository;

import com.my.project.reactivedemo.domain.entity.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo,Long> {
}
