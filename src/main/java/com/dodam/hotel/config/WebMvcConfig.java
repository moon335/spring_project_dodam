package com.dodam.hotel.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dodam.hotel.handler.LoginInterceptor;
import com.dodam.hotel.handler.ManagerLoginInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private ManagerLoginInterceptor managerLoginInterceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/uploads/**")
				.addResourceLocations("file:///C:\\upload_file\\dodam\\upload/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 유저 로그인 인터셉터
		List<String> patterns = new ArrayList<>();
		patterns.add("/myPage");
		patterns.add("/myPageProc");
		patterns.add("/joinMembership");
		patterns.add("/myReplys");
		patterns.add("/couponList");
		patterns.add("/reserveDining");
		patterns.add("/pay/**");
		patterns.add("/question/qnaProc");
		patterns.add("/question/qnaPage");
		patterns.add("/reserve");
		patterns.add("/selectDate");
		patterns.add("/reserveRoom");
		patterns.add("/myReservations");
		patterns.add("/search");
		patterns.add("/api/myInfo");
		patterns.add("/question/chatRoom");
		registry.addInterceptor(loginInterceptor).addPathPatterns(patterns);
		
		// 매니저 로그인 인터셉터
		List<String> managerPatterns = new ArrayList<>();
		managerPatterns.add("/manager/**");
		managerPatterns.add("/event/notice");
		managerPatterns.add("/event/event-insert");
		managerPatterns.add("/event/updateEventPage/**");
		managerPatterns.add("/event/updateEvent/**");
		managerPatterns.add("/event/deleteEvent/**");
		registry.addInterceptor(managerLoginInterceptor).addPathPatterns(managerPatterns)
			.excludePathPatterns("/manager/managerPage").excludePathPatterns("/manager/managerSignInProc");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
} // end of class
