package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtmCard {


    @Id
    private String cardNo;

    private  String cardHolderName;

    private String expiryDate;

    @Transient
    private Integer cvv;

    @ManyToOne
    private  User user;

    @OneToMany(mappedBy = "atmCard",cascade = CascadeType.ALL)
    private List<Payment> payment;

}
