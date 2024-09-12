package com.tenco.movie.service;

import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.Subregions;
import com.tenco.movie.repository.model.Theater;

import lombok.RequiredArgsConstructor;

import com.tenco.movie.repository.interfaces.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public List<Regions> getAllRegions() {
        return cinemaRepository.findAllRegions();
    }
    
    public List<Subregions> getSubregionsByRegionId(int regionId) {
        return cinemaRepository.findByRegionId(regionId);
    }
    
    public List<Theater> getTheatersBySubregionId(int subregionId) {
        return cinemaRepository.findTheatersBySubregionId(subregionId);
    }
}


