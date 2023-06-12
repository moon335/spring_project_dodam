package com.dodam.hotel.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.handler.exception.LoginException;
import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.handler.exception.ManagerLoginException;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.util.Define;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto) session.getAttribute(Define.PRINCIPAL);
		Manager managerPrincipal = (Manager)session.getAttribute(Define.MANAGERPRINCIPAL);
		if(user == null && managerPrincipal != null) {
			throw new ManagerCustomRestFullException("유저 계정만 이용 가능합니다.", HttpStatus.FORBIDDEN);
		}
		if(user == null) {
			throw new LoginException("로그인 후 이용 가능한 서비스 입니다.", HttpStatus.BAD_REQUEST);
		}
		return true;
	}
	
}
