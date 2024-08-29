package com.tenco.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBCreditsDTO {
    private int id;
    private List<CastDTO> cast;
    private List<CrewDTO> crew;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CastDTO {
        private int castId;
        private String character;
        private String creditId;
        private int gender;
        private int id;
        private String name;
        private int order;
        private String profilePath;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CrewDTO {
        private String creditId;
        private String department;
        private int gender;
        private int id;
        private String job;
        private String name;
        private String profilePath;
    }
}
