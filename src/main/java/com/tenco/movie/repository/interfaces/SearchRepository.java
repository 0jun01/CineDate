package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.MergedAnnotations.Search;

@Mapper
public interface SearchRepository {
	
	int searchTitle(Search search);
	int titleCount(Search search);
	
}
