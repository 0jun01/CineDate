package com.tenco.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.repository.model.Search;
import com.tenco.movie.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {


	
	@Autowired
	private SearchService searchService;

	/**
	 * 메인 검색 기능
	 * 
	 * @param model
	 * @param titleSearch
	 * @return
	 * @author 형정
	 */
	@GetMapping("/search")
	public String searchMovieList(Model model, @RequestParam(name = "search") String title) {

		List<Search> searchList = searchService.searchMoiveTitle(title);
		model.addAttribute("searchList", searchList);

		return "search/search";

	}

}
