package com.bookmyshow.controller;

import com.bookmyshow.payloads.TheatreDto;
import com.bookmyshow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity <TheatreDto> createTheatre(@RequestBody TheatreDto theatreDto){

        TheatreDto createdTheatreDto  = this.theatreService.createTheatre(theatreDto);

        return  new ResponseEntity<TheatreDto>(createdTheatreDto, HttpStatus.CREATED);

    }


}
