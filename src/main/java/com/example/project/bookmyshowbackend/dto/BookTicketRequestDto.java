package com.example.project.bookmyshowbackend.dto;

import com.example.project.bookmyshowbackend.enums.SeatType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder

public class BookTicketRequestDto {
    Set<String> requestedSeats; //user give this

    int ShowId; //For which show I want to book ticket :- showEntity
    int id; //userId
    SeatType seatType; //theaterseatentity

}
