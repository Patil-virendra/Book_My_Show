package com.bookmyshow.entity;

import com.bookmyshow.utility.AppConstants;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

   private List<String> availableSeats = new ArrayList<>(AppConstants.TOTAL_SEATS);

    private LocalTime showTime;

    @ManyToOne
    private  Movie movie;

    @ManyToOne
    private  Theatre theatre;

    @OneToMany(mappedBy = "shows",cascade =CascadeType.ALL)
    private List<Booking> bookings=new ArrayList<>();

}