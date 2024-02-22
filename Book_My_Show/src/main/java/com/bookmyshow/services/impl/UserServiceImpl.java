package com.bookmyshow.services.impl;

import com.bookmyshow.entity.Role;
import com.bookmyshow.entity.User;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.payloads.UserDto;
import com.bookmyshow.repositories.RoleRepo;
import com.bookmyshow.repositories.UserRepo;
import com.bookmyshow.services.UserService;
import com.bookmyshow.utility.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {

        User user=this.modelMapper.map(userDto, User.class);
        String userId= UUID.randomUUID().toString();
        user.setId(userId);
     //   User user = modelMapper.map(userDto, User.class);
      //  user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findById(AppConstants.NORMAL_USER_ID).get();
        user.getRoles().add(role);

        User registerUser= this.userRepo.save(user);
        return  this.modelMapper.map(registerUser, UserDto.class);
    }

    //update user
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
       User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

             user.setFname(userDto.getFname());
             user.setLname(userDto.getLname());
             user.setDob(userDto.getDob());
             user.setEmail(userDto.getEmail());
             user.setPassword(userDto.getPassword());

             User updatedUser= this.userRepo.save(user);

        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    //get single user
    @Override
    public UserDto getUserById(String userId) {

       User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.modelMapper.map(user, UserDto.class);
    }

    //get All User
    @Override
    public List<UserDto> getAllUser() {
       List<User>users= this.userRepo.findAll();
        List<UserDto>userDto= users.stream().map( u1->this.modelMapper.map(u1,UserDto.class)).collect(Collectors.toList());

        return userDto;
    }

    //delete user
    @Override
    public void deleteUserById(String userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }
    //get single user
   // @Override
//    public UserDto getUserByName(String userName) {
//
//
//        User user= this.userRepo.findByFname(userName);
//
//        return this.modelMapper.map(user, UserDto.class);
//    }

//    @Override
//    public List<UserDto> searchUserByEmailAndFname(String email, String fname) {
//
//
//        this.modelMapper.map()
//
//       = this.userRepo.findByEmailAndFname(email,fname);
//
//        return null;
    }



