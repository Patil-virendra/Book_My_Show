package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {


    @Id
    private String foodName;

    private Integer foodPrice;

    @OneToOne(mappedBy = "food",cascade = CascadeType.ALL)
    private Booking booking;


   }
