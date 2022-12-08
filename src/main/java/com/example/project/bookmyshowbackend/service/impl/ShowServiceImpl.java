package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Models.*;
import com.example.project.bookmyshowbackend.Repositories.MovieRepository;
import com.example.project.bookmyshowbackend.Repositories.ShowRepository;
import com.example.project.bookmyshowbackend.Repositories.ShowSeatRepository;
import com.example.project.bookmyshowbackend.Repositories.TheaterRepository;
import com.example.project.bookmyshowbackend.converter.ShowConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.ShowEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.ShowResponseDto;
import com.example.project.bookmyshowbackend.service.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShowServiceImpl implements ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;


    @Override
    public ShowResponseDto addShow(ShowEntryDto showEntryDto) {

        //We have made the partial Show Entity Object..

        //Goal : Set the Movie and the Theater Entities and not the Dto
        ShowEntity showEntity = ShowConverter.convertDtoToEntity(showEntryDto);

        //MovieEntity
        MovieEntity movieEntity = movieRepository.findById(showEntryDto.getMovieResponseDto().getId()).get();

        //TheaterEntity
        TheaterEntity theaterEntity = theaterRepository.findById(showEntryDto.getTheaterResponseDto().getId()).get();

        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);
        showEntity = showRepository.save(showEntity);

        //We need to pass the list of the theater seats
        generateShowEntitySeats(theaterEntity.getSeats(), showEntity);
        ShowResponseDto showResponseDto = ShowConverter.converEntityToDto(showEntity, showEntryDto);

        return showResponseDto;
    }

    @Override
    public ShowResponseDto getShow(int id) {
        ShowEntity showEntity = showRepository.findById(id).get();
        ShowResponseDto showResponseDto = ShowConverter.converEntityToDto(showEntity);
        return showResponseDto;

    }

    private void generateShowEntitySeats(List<TheaterSeatEntity> theaterSeatEntityList, ShowEntity showEntity) {
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();
//        log.info(String.valueOf(theaterSeatsEntityList));
//        log.info("The list of theaterEntity Seats");
//        for(TheaterSeatsEntity tse: theaterSeatsEntityList){
//            log.info(tse.toString());
//        }
        for (TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList) {
            ShowSeatEntity showSeatEntity = ShowSeatEntity.builder().seatNumber(theaterSeatEntity.getSeatNumber())
                    .seatType(theaterSeatEntity.getSeatType())
                    .rate(theaterSeatEntity.getRate())
                    .build();
            showSeatEntityList.add(showSeatEntity);
        }
        for (ShowSeatEntity showSeatEntity : showSeatEntityList) {
            showSeatEntity.setShow(showEntity);
        }
        showSeatRepository.saveAll(showSeatEntityList);
        showEntity.setSeats(showSeatEntityList);
    }
}