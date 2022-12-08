package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.Models.UserEntity;
import com.example.project.bookmyshowbackend.dto.EntryRequest.UserEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.UserResponseDto;


public class UserConverter {
    public static UserEntity covertDtoToEntity (UserEntryDto userEntryDto){

        return UserEntity.builder().name(userEntryDto.getName()).mobileNo(userEntryDto.getMobNo()).build();
    }
    public static UserResponseDto convertEntityToDto (UserEntity user){

        return  UserResponseDto.builder().id(user.getId()).name(user.getName()).mobNo(user.getMobileNo()).build();
    }
}
