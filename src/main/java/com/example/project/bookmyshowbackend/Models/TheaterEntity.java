package com.example.project.bookmyshowbackend.Models;


import com.example.project.bookmyshowbackend.enums.SeatType;
import com.example.project.bookmyshowbackend.enums.TheaterType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theater")
@Builder
@Entity
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    @JsonIgnore
    List<ShowEntity> shows;

    TheaterType theaterType;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<TheaterSeatEntity> seats;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    TicketEntity ticketEntity;
}
