package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.Models.MovieEntity;
import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieNameandIdObject;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;

public class MovieConverter {
    public static MovieEntity convertDtoToEntity (MovieEntryDto movieEntryDto){

        return MovieEntity.builder().name(movieEntryDto.getName()).releaseDate(movieEntryDto.getReleaseDate()).build();
    }
    public static MovieResponseDto convertEntityToDto (MovieEntity movieEntity){
        return MovieResponseDto.builder().id(movieEntity.getId())
                .name(movieEntity.getName()).releaseDate(movieEntity.getReleaseDate()).build();
    }
    public static MovieNameandIdObject convertEntityToThisObject (MovieEntity movieEntity){

        return MovieNameandIdObject.builder().id(movieEntity.getId())
                .name(movieEntity.getName()).build();
    }
}
