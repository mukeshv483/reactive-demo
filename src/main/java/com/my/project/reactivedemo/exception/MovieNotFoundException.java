package com.my.project.reactivedemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class MovieNotFoundException extends ErrorResponseException {

    public MovieNotFoundException(Long bookmarkId) {
        super(HttpStatus.NOT_FOUND, asProblemDetail("Movie with id " + bookmarkId + " not found"), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Movie Not Found");
        problemDetail.setType(URI.create("https://api.movie.com/errors/not-found"));
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
