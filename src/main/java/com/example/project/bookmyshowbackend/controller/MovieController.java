package com.example.project.bookmyshowbackend.controller;

import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieNameandIdObject;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;
import com.example.project.bookmyshowbackend.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @PostMapping("add")
    public MovieResponseDto addMovie(@RequestBody()MovieEntryDto movieEntryDto){
        MovieResponseDto movieResponseDto = movieService.addMovie(movieEntryDto);
        return movieResponseDto;
    }

    @GetMapping("/get/{id}")
    public MovieNameandIdObject getMovie(@PathVariable Integer id){
        return movieService.getNameAndId(id);
    }
}
