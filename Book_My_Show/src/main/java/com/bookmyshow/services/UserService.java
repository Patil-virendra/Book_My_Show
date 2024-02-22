package com.bookmyshow.services;

import com.bookmyshow.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,String userId);

    UserDto getUserById(String userId);
    List<UserDto> getAllUser();

    void deleteUserById(String userId);

  //  List<UserDto> searchUserByEmailAndFname(String fname ,String email);

  //  UserDto getUserByName(String userName);
}
