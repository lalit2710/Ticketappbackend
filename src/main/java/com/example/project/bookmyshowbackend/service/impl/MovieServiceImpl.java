package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Models.MovieEntity;
import com.example.project.bookmyshowbackend.Repositories.MovieRepository;
import com.example.project.bookmyshowbackend.converter.MovieConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieNameandIdObject;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;
import com.example.project.bookmyshowbackend.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public MovieResponseDto addMovie(MovieEntryDto movieEntryDto) {

        MovieResponseDto movieResponseDto = null;

        if(movieRepository.existsByName(movieEntryDto.getName())){

            movieResponseDto.setName("This movie is already Exist");
            return movieResponseDto;
        }
        log.info("In the function add movie "+ movieEntryDto);

        MovieEntity movieEntity = MovieConverter.convertDtoToEntity(movieEntryDto);
        movieEntity = movieRepository.save(movieEntity);
        movieResponseDto = MovieConverter.convertEntityToDto(movieEntity);
        return movieResponseDto;
    }

    @Override
    public MovieResponseDto getMovie(int id) {
        MovieEntity movieEntity = movieRepository.findById(id).get();
        MovieResponseDto movieResponseDto = MovieConverter.convertEntityToDto(movieEntity);

        return movieResponseDto;
    }

    @Override
    public MovieNameandIdObject getNameAndId(int id) {
        MovieEntity movieEntity = movieRepository.findById(id).get();
        MovieNameandIdObject movieNameandIdObject = MovieConverter.convertEntityToThisObject(movieEntity);
        return movieNameandIdObject;
    }
}
