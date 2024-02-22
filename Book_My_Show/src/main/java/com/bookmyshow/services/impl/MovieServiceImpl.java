package com.bookmyshow.services.impl;

import com.bookmyshow.entity.Movie;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.payloads.MovieDto;
import com.bookmyshow.repositories.MovieRepo;
import com.bookmyshow.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
     private MovieRepo movieRepo;

    @Autowired
    private ModelMapper modelMapper;

    //create movie
    @Override
    public MovieDto createMovie(MovieDto movieDto) {

        Movie movie=this.modelMapper.map(movieDto, Movie.class);
            String movieId= UUID.randomUUID().toString();
        movie.setId(movieId);
        Movie movie1=this.movieRepo.save(movie);
        return this.modelMapper.map(movie1, MovieDto.class);
    }


    //get  Single Movies By Id
    @Override
    public MovieDto getMovieById(String movieId) {

        Movie movie=this.movieRepo.findById(movieId).orElseThrow(()->new ResourceNotFoundException("Movie","Id",movieId));

        return this.modelMapper.map(movie, MovieDto.class);
    }

     //get All Movies
    @Override
    public List<MovieDto> getAllMovie() {
       List<Movie> movies= this.movieRepo.findAll();
        List<MovieDto>movieDtos =movies.stream().map(m->this.modelMapper.map(m,MovieDto.class)).collect(Collectors.toList());
        return movieDtos;
    }
     //search movie by name
    @Override
    public List<MovieDto> getMovieBySearch(String keywords) {

        List<Movie> movies  =this.movieRepo.findByMovieNameContaining(keywords);
        List<MovieDto>movieDtos = movies.stream().map((m1)->this.modelMapper.map(m1,MovieDto.class)).collect(Collectors.toList());

         //        List<Movie> searchMovies1= this.movieRepo.searchByTitle("%"+keywords+"%");
//        List<MovieDto> movieDtos1=searchPosts1.stream().map((m11)->this.modelMapper.map(m11,MovieDto.class)).collect(Collectors.toList());


        return movieDtos;
    }
}
