package com.example.project.bookmyshowbackend.controller;

import com.example.project.bookmyshowbackend.dto.BookTicketRequestDto;
import com.example.project.bookmyshowbackend.dto.TicketDto;
import com.example.project.bookmyshowbackend.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    TicketServiceImpl ticketService;

    @PostMapping("/add")
    public TicketDto addTicket(@RequestBody()BookTicketRequestDto bookTicketRequestDto){
        return ticketService.bookTicket(bookTicketRequestDto);
    }

    @GetMapping("/get/{id}")
    public TicketDto getTicket(@PathVariable Integer id){
        return ticketService.getTicket(id);
    }
}
