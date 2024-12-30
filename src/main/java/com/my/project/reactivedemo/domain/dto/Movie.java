package com.my.project.reactivedemo.domain.dto;

import lombok.*;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String name;
    private Long id;
}
