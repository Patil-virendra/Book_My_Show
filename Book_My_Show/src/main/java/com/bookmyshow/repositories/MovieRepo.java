package com.bookmyshow.repositories;

import com.bookmyshow.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie,String> {


    List<Movie> findByMovieNameContaining(String keywords);

    @Query("select p from Movie p where p.movieName like:key")
    List<Movie>searchByMovieName(@Param("key") String title);


    Optional<Movie> findByMovieName(String movieName);

}
