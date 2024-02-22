package com.bookmyshow.controller;

import com.bookmyshow.entity.User;
import com.bookmyshow.exception.ApiException;
import com.bookmyshow.payloads.JwtAuthRequest;
import com.bookmyshow.payloads.JwtAuthResponse;
import com.bookmyshow.payloads.UserDto;
import com.bookmyshow.repositories.UserRepo;
import com.bookmyshow.security.JwtUtil;
import com.bookmyshow.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtAuthRequest authenticationRequest) throws Exception {

        User user = new User();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            user = userRepo.findByEmail(authenticationRequest.getUsername())
                    .orElseThrow(()->new ApiException("Incorrect username or password"));
        }
        catch (BadCredentialsException e) {
            throw new ApiException("Incorrect username or password");
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAuth(jwt);
        response.setUser(modelMapper.map(user, UserDto.class));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(/*@Valid*/ @RequestBody UserDto userDto){
        UserDto newUser = userService.registerUser(userDto);
        return new ResponseEntity<UserDto>(newUser, HttpStatus.CREATED);
    }

}
