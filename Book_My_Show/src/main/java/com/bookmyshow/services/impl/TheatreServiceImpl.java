package com.bookmyshow.services.impl;

import com.bookmyshow.entity.Theatre;
import com.bookmyshow.payloads.TheatreDto;
import com.bookmyshow.repositories.TheatreRepo;
import com.bookmyshow.services.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreServiceImpl  implements TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TheatreDto createTheatre(TheatreDto theatreDto) {


         Theatre theatre=this.modelMapper.map(theatreDto, Theatre.class);
        Theatre theatre1= this.theatreRepo.save(theatre);

        return this.modelMapper.map(theatre1, TheatreDto.class);
    }
}
