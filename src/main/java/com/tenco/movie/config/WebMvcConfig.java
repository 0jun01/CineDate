package com.tenco.movie.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	// 코드추가 
	// C:\Users\GGG\Documents\Lightshot\a.png <-- 서버 컴퓨터상에 실제 이미지 경로지만 
	// 프로젝트 상에서(클라이언트가 HTML 소스로 보이는 경로는) /images/uploads/**
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:\\C:\\Users\\KDP\\git\\CineDate\\src\\main\\resources\\static\\DateProfileIMAGE/");
		registry.addResourceHandler("/eventImage/**")
		.addResourceLocations("file:\\C:\\Users\\KDP\\git\\CineDate\\src\\main\\resources\\static\\eventIMAGE/");
		
		
	}
	
	
}
