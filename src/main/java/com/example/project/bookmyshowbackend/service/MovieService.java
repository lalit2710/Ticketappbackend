package com.example.project.bookmyshowbackend.service;

import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieNameandIdObject;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;

public interface MovieService {
    MovieResponseDto addMovie(MovieEntryDto movieEntryDto);//Add Movie
    MovieResponseDto getMovie(int id);//get Movie
    MovieNameandIdObject getNameAndId(int id);
}
