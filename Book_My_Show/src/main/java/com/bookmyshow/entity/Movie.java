package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String id;

    private String movieName;

    private String moviePoster;

    private LocalDate releaseDate;

    private String category;

    private String genre;

    private String rating;

    private String  country;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Booking>booking=new ArrayList<>();

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private  List<Shows>shows=new ArrayList<>();
}
