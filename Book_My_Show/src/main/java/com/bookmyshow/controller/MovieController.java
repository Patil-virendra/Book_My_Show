package com.bookmyshow.controller;

import com.bookmyshow.payloads.MovieDto;
import com.bookmyshow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@EnableWebSecurity
@Validated
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<MovieDto>createMovie(@RequestBody MovieDto movieDto)
    {
        MovieDto movieDto1=this.movieService.createMovie(movieDto);

        return new ResponseEntity<MovieDto>(movieDto1, HttpStatus.CREATED);

    }
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto>getMovie(@PathVariable String movieId)
    {
        MovieDto movieDto1=this.movieService.getMovieById(movieId);

        return new ResponseEntity<MovieDto>(movieDto1, HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<List<MovieDto>>getAllMovie()
    {
        List<MovieDto> movieDtos=this.movieService.getAllMovie();

        return new ResponseEntity<List<MovieDto>>(movieDtos, HttpStatus.OK);

    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<MovieDto>>getMovieBySearch(@PathVariable("keyword") String keyword){

        List<MovieDto>movieDtos = this.movieService.getMovieBySearch(keyword);

        return   new ResponseEntity<List<MovieDto>>(movieDtos,HttpStatus.OK);

    }



}
