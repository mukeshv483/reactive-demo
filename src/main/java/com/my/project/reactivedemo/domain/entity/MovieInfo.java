package com.my.project.reactivedemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class MovieInfo {
    private String movieInfoId;
    private String name;
    private Integer year;
    private List<String> cast;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
}
