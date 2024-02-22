package com.bookmyshow.services;

import com.bookmyshow.payloads.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);

    MovieDto getMovieById(String movieId);

    List<MovieDto>getAllMovie();

    List<MovieDto> getMovieBySearch(String keywords);

}
