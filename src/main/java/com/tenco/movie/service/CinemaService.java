package com.tenco.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tenco.movie.repository.interfaces.CinemaRepository;
import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;
import com.tenco.movie.repository.model.Theater;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaService {

	private final CinemaRepository cinemaRepository;

<<<<<<< HEAD
<<<<<<< HEAD
    public List<Regions> getAllRegions() {
        return cinemaRepository.findAllRegions();
    }
    
    public List<SubRegions> getSubregionsByRegionId(int regionId) {
        return cinemaRepository.findByRegionId(regionId);
    }
    
    public List<Theater> getTheatersBySubregionId(int subregionId) {
        return cinemaRepository.findTheatersBySubregionId(subregionId);
    }
=======
=======
>>>>>>> 841cd954bbaf41e3af36dcc2d2eadc842ff592cc
	public List<Regions> getAllRegions() {
		return cinemaRepository.findAllRegions();
	}

	public List<SubRegions> getSubregionsByRegionId(int regionId) {
		return cinemaRepository.findByRegionId(regionId);
	}

	public List<Theater> getTheatersBySubregionId(int subregionId) {
		return cinemaRepository.findTheatersBySubregionId(subregionId);
	}
<<<<<<< HEAD
>>>>>>> 2d44f600c135731dcfdfe87ac816cd9d3c5133b0
}
=======
	
}
>>>>>>> 841cd954bbaf41e3af36dcc2d2eadc842ff592cc
