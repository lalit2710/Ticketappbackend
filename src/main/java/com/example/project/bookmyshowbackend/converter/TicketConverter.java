package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.Models.TicketEntity;
import com.example.project.bookmyshowbackend.dto.TicketDto;

public class TicketConverter {
    public static TicketDto convertEntityToDto (TicketEntity ticketEntity){
        return TicketDto.builder().id((int) ticketEntity.getId()).alloted_seats(ticketEntity.
                getAlloted_seats()).amount(ticketEntity.getAmount()).build();
    }
}
