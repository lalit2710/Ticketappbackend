package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Models.UserEntity;
import com.example.project.bookmyshowbackend.Repositories.UserRepository;
import com.example.project.bookmyshowbackend.converter.UserConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.UserEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.UserResponseDto;
import com.example.project.bookmyshowbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public void addUser(UserEntryDto userEntryDto) {

        UserEntity userEntity = UserConverter.covertDtoToEntity(userEntryDto);
        userEntity = userRepository.save(userEntity);
    }

    @Override
    public UserResponseDto getUser(int id) {
        UserEntity user = new UserEntity(); //by default user

        UserEntity userEntity = userRepository.findById(id).get();

        UserResponseDto userResponseDto = UserConverter.convertEntityToDto(userEntity);
        return userResponseDto;
    }
}
