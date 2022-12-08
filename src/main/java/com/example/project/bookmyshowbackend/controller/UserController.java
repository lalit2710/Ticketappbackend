package com.example.project.bookmyshowbackend.controller;

import com.example.project.bookmyshowbackend.Repositories.UserRepository;
import com.example.project.bookmyshowbackend.dto.EntryRequest.UserEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.UserResponseDto;
import com.example.project.bookmyshowbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("add")
    public ResponseEntity addUser(@RequestBody() UserEntryDto userEntryDto){
        userService.addUser(userEntryDto);

        return new ResponseEntity<>("Added a user successfully", HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "id")int id){
        UserResponseDto userResponseDto = userService.getUser(id);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }
}
