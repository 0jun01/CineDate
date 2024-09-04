package com.tenco.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailDTO {
    @JsonProperty("movieInfoResult") // JSON 필드 "movieInfoResult"와 매핑
    private MovieInfoResult movieInfoResult;
    
    @JsonProperty("source") 
    private String source; // 데이터 출처 )

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfoResult {
        @JsonProperty("movieInfo") 
        private MovieInfo movieInfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfo {
        @JsonProperty("movieCd")
        private String movieCd; // 영화 코드 (예: 20249188)
        
        @JsonProperty("movieNm") 
        private String movieNm; // 영화 제목 (한글)
        
        @JsonProperty("movieNmEn") 
        private String movieNmEn; // 영화 제목 (영어)
        
        @JsonProperty("showTm") 
        private String showTm; // 상영 시간 (분 단위)
        
        @JsonProperty("openDt") 
        private String openDt; // 개봉 날짜 (형식: YYYYMMDD)
        
        @JsonProperty("prdtStatNm") 
        private String prdtStatNm; // 제작 상태 (예: 개봉)
        
        @JsonProperty("typeNm") 
        private String typeNm; // 영화 유형 (예: 장편)
        
        @JsonProperty("nations") 
        private List<NationDTO> nations; // 제작 국가 리스트
        
        @JsonProperty("genres") 
        private List<GenreDTO> genres; // 장르 리스트
        
        @JsonProperty("directors") 
        private List<PersonDTO> directors; // 감독 리스트
        
        @JsonProperty("actors") 
        private List<PersonDTO> actors; // 배우 리스트
        
        @JsonProperty("audits") 
        private List<AuditDTO> audits; // 심의 정보 리스트
        
        @JsonProperty("staffs") 
        private List<StaffDTO> staffs; // 제작진 리스트
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NationDTO {
        @JsonProperty("nationNm") 
        private String nationNm; // 국가명
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GenreDTO {
        @JsonProperty("genreNm") // JSON 필드 "genreNm"와 매핑
        private String genreNm; // 장르명
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PersonDTO {
        @JsonProperty("peopleNm") // JSON 필드 "peopleNm"와 매핑 (감독과 배우 모두)
        private String peopleNm; // 이름 (감독 또는 배우)
        
        @JsonProperty("peopleNmEn") // JSON 필드 "peopleNmEn"와 매핑 (감독과 배우 모두)
        private String peopleNmEn; // 영어 이름 (감독 또는 배우)
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AuditDTO {
        @JsonProperty("auditNo") 
        private String auditNo; // 심의 번호
        
        @JsonProperty("watchGradeNm") // JSON 필드 "watchGradeNm"와 매핑
        private String watchGradeNm; // 관람 등급
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StaffDTO {
        @JsonProperty("peopleNm") 
        private String peopleNm; // 제작진 이름
        
        @JsonProperty("peopleNmEn") 
        private String peopleNmEn; // 제작진 영어 이름
        
        @JsonProperty("staffRoleNm") 
        private String staffRoleNm; // 역할
    }
}