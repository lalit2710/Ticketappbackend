package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.Models.ShowEntity;
import com.example.project.bookmyshowbackend.dto.EntryRequest.ShowEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.ShowResponseDto;

public class ShowConverter {
    public static ShowEntity convertDtoToEntity (ShowEntryDto showEntryDto){
        return ShowEntity.builder().showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime()).build();
    }
    public static ShowResponseDto converEntityToDto (ShowEntity showEntity,ShowEntryDto showEntryDto){
        return ShowResponseDto.builder().id(showEntity.getId())
                .showDate(showEntity.getShowDate())
                .showTime(showEntity.getShowTime()).build();

    }
    public static ShowResponseDto converEntityToDto(ShowEntity showEntity) {
        return ShowResponseDto.builder()
                .id(showEntity.getId())
                .showDate(showEntity.getShowDate())
                .showTime(showEntity.getShowTime())
                .movieName(showEntity.getMovie().getName())
                .theaterName(showEntity.getTheater().getName())
                .build();
    }
}
