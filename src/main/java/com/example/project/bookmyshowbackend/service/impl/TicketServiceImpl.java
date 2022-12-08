package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Models.ShowEntity;
import com.example.project.bookmyshowbackend.Models.ShowSeatEntity;
import com.example.project.bookmyshowbackend.Models.TicketEntity;
import com.example.project.bookmyshowbackend.Models.UserEntity;
import com.example.project.bookmyshowbackend.Repositories.ShowRepository;
import com.example.project.bookmyshowbackend.Repositories.TicketRepository;
import com.example.project.bookmyshowbackend.Repositories.UserRepository;
import com.example.project.bookmyshowbackend.converter.TicketConverter;
import com.example.project.bookmyshowbackend.dto.BookTicketRequestDto;
import com.example.project.bookmyshowbackend.dto.TicketDto;
import com.example.project.bookmyshowbackend.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Override
    public TicketDto getTicket(int id) {

        TicketEntity ticketEntity = ticketRepository.findById(id).get();

        TicketDto ticketDto = TicketConverter.convertEntityToDto(ticketEntity);
        return ticketDto;
    }

    @Override
    public TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto) {

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.
                getId()).get();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId())
                .get();
        Set<String>requestSeats = bookTicketRequestDto.getRequestedSeats();
        List<ShowSeatEntity> showSeatEntityList = showEntity.getSeats();
       // log.info("getting Seats", showSeatEntityList);
        //option - 1
        List<ShowSeatEntity> bookedSeats = showSeatEntityList
                .stream()
                .filter(seat -> seat.getSeatType().equals(bookTicketRequestDto.getSeatType())&&!seat.isBooked()&&
                        requestSeats.contains(seat.getSeatNumber()))
                .collect(Collectors.toList());

        //Option 2
//        List<ShowSeatsEntity> bookedSeats1 = new ArrayList<>();
//
//        for(ShowSeatsEntity seat :showSeatsEntityList){
//
//            if(!seat.isBooked()&&seat.getSeatType().
//            equals(bookTicketRequestDto.getSeatType())
//            &&requestSeats.contains(seat.getSeatNumber())){
//                bookedSeats1.add(seat);
//            }
//        }
        if(bookedSeats.size() != requestSeats.size()){
            throw new Error("All Seats not available");
        }
        //step 2
        TicketEntity ticketEntity = TicketEntity.builder()
                .user(userEntity)
                .show(showEntity)
                .seats(bookedSeats)
                .build();
        //step 3:
        double amount = 0;
        for(ShowSeatEntity showSeatEntity:bookedSeats){
            showSeatEntity.setBooked(true);
            showSeatEntity.setBookedAt(new Date());
            showSeatEntity.setTicket(ticketEntity);

            amount = amount+showSeatEntity.getRate();
        }
        ticketEntity.setCreatedOn(new Date());
        String bookedSeatsStr = convertListOfSeatsEntityToString(bookedSeats);
//        for(ShowSeatsEntity seats : bookedSeats){
//            bookedSeatsStr+=seats.getSeatNumber()+" ";
//        }
        ticketEntity.setAlloted_seats(bookedSeatsStr);
        ticketEntity.setAmount((int)amount);
        //Connect in show & user
        showEntity.getTickets().add(ticketEntity);

        //Add the ticket in the tickets list of the user Entity
        userEntity.getListOfTickets().add(ticketEntity);
        ticketEntity = ticketRepository.save(ticketEntity);

        return TicketConverter.convertEntityToDto(ticketEntity);
    }

    public String convertListOfSeatsEntityToString(List<ShowSeatEntity> bookedSeats) {
        String str = "";
        for(ShowSeatEntity showSeatEntity : bookedSeats){
            str += showSeatEntity.getSeatNumber()+" ";
        }
        return  str;
    }
}
