package com.example.project.bookmyshowbackend.dto.ResponseDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ShowResponseDto {
    int id;
    LocalDate showDate;
    LocalTime showTime;

    String movieName;
    String theaterName;

    MovieResponseDto movieResponseDto;

    TheaterResponseDto theaterResponseDto;

}
