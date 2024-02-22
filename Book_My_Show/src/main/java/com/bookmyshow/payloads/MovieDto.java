package com.bookmyshow.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String id;

    private String moviePoster;

    private String movieName;

    private LocalDate releaseDate;

    private String category;

    private String genre;

    private String rating;

    private String  country;

    public MovieDto(String id) {
        this.id = id;
    }
}
