package com.bookmyshow.controller;

import com.bookmyshow.payloads.ApiResponse;
import com.bookmyshow.payloads.UserDto;
import com.bookmyshow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create user //this is used in authController
//    @PostMapping("/register")
//    public ResponseEntity<UserDto>registerUser( @Valid @RequestBody UserDto userDto){
//
//        UserDto userDto1=this.userService.registerUser(userDto);
//        return  new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
//    }

    //PUT-update user
    @PutMapping("/{userId}")
    public  ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto , @PathVariable("userId") String uid){
        UserDto updateUser = this.userService.updateUser(userDto,uid);
        return  ResponseEntity.ok(updateUser);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUserById(@PathVariable String userId){
       UserDto userDto= this.userService.getUserById(userId);
       return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity <List<UserDto>>getAllUser(){
       List<UserDto> userDto= this.userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(userDto,HttpStatus.OK);

    }


    // delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable String userId)
    {
       this.userService.deleteUserById(userId);
           return  new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }


}
