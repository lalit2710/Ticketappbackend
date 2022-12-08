package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Models.TheaterEntity;
import com.example.project.bookmyshowbackend.Models.TheaterSeatEntity;
import com.example.project.bookmyshowbackend.Repositories.TheaterRepository;
import com.example.project.bookmyshowbackend.Repositories.TheaterSeatRepository;
import com.example.project.bookmyshowbackend.converter.TheaterConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.TheaterEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.TheaterResponseDto;
import com.example.project.bookmyshowbackend.enums.SeatType;
import com.example.project.bookmyshowbackend.enums.TheaterType;
import com.example.project.bookmyshowbackend.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;


    @Override
    public TheaterResponseDto addTheater(TheaterEntryDto theaterEntryDto) {
        TheaterEntity theaterEntity = TheaterConverter.convertDtoToEntity(theaterEntryDto);
        //Create Seats
        List<TheaterSeatEntity> seats = creatTheaterSeats();
        //I need to set the theaterId for all these seats
        theaterEntity.setSeats(seats);
        theaterEntity.setShows(null);

        for (TheaterSeatEntity theaterSeatEntity : seats){
            theaterSeatEntity.setTheater(theaterEntity);
        }
        theaterEntity.setTheaterType(TheaterType.SINGLE);
        theaterEntity = theaterRepository.save(theaterEntity);
        TheaterResponseDto theaterResponseDto = TheaterConverter.convertEntityToDto(theaterEntity);
        return theaterResponseDto;
    }
    List<TheaterSeatEntity> creatTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();
        seats.add(getTheaterSeat("1A",100,SeatType.CLASSIC));
        seats.add(getTheaterSeat("1B",100,SeatType.CLASSIC));
        seats.add(getTheaterSeat("1C",100,SeatType.CLASSIC));
        seats.add(getTheaterSeat("1D",100,SeatType.CLASSIC));
        seats.add(getTheaterSeat("1E",100,SeatType.CLASSIC));

        seats.add(getTheaterSeat("2A",100,SeatType.PREMIUM));
        seats.add(getTheaterSeat("2B",100,SeatType.PREMIUM));
        seats.add(getTheaterSeat("2C",100,SeatType.PREMIUM));
        seats.add(getTheaterSeat("2D",100,SeatType.PREMIUM));
        seats.add(getTheaterSeat("2E",100,SeatType.PREMIUM));

        theaterSeatRepository.saveAll(seats);
        return seats;
    }
    TheaterSeatEntity getTheaterSeat(String seatName,int rate,SeatType seatType){
        return TheaterSeatEntity.builder().seatNumber(seatName).rate(rate).seatType(seatType).build();
    }

    @Override
    public TheaterResponseDto getTheater(int id) {
        TheaterEntity theaterEntity = theaterRepository.findById(id).get();
        TheaterResponseDto theaterResponseDto = TheaterConverter.convertEntityToDto(theaterEntity);
        return theaterResponseDto;
    }
}
