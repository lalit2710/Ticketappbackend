package com.example.project.bookmyshowbackend.Models;

import java.util.Date;

import javax.persistence.*;

import com.example.project.bookmyshowbackend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "show_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    @Column(name = "is_booked", columnDefinition = "bit(1) default 0", nullable = false)
    private boolean booked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_at")
    private Date bookedAt;

    @ManyToOne
    @JsonIgnore
    private ShowEntity show;

    @ManyToOne
    @JsonIgnore
    private TicketEntity ticket;
}