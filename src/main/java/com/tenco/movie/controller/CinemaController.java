package com.tenco.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;
import com.tenco.movie.repository.model.Theater;
import com.tenco.movie.service.CinemaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class CinemaController {

	private final CinemaService cinemaService;

	@GetMapping("/cinema")
	public String showCinemaPage(@RequestParam(value = "id", required = false) Integer regionId,
			@RequestParam(value = "subregionId", required = false) Integer subregionId, Model model) {
		List<Regions> regions = cinemaService.getAllRegions();
		model.addAttribute("regions", regions);

// 기본값 설정
		if (regionId == null) {
			regionId = 1;
		}

        if (subregionId == null) {
            // 기본 상위 지역의 첫 하위 지역 ID를 설정
            List<SubRegions> subregions = cinemaService.getSubregionsByRegionId(regionId);
            if (!subregions.isEmpty()) {
                subregionId = subregions.get(0).getId();
            }
        }

        List<SubRegions> subregions = cinemaService.getSubregionsByRegionId(regionId);
        model.addAttribute("subregions", subregions);
        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedSubregionId", subregionId);


// 영화관 정보 추가
		List<Theater> theaters = cinemaService.getTheatersBySubregionId(subregionId);
		model.addAttribute("theaters", theaters);

		return "/cinema/cinemaPage";
	}

}