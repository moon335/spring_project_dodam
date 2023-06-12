package com.dodam.hotel.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dodam.hotel.handler.exception.ManagerLoginException;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.util.Define;

@Component
public class ManagerLoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Manager principal = (Manager) session.getAttribute(Define.MANAGERPRINCIPAL);
		if(principal == null) {
			throw new ManagerLoginException("로그인 후 이용 가능한 서비스 입니다.", HttpStatus.FORBIDDEN);
		}
		return true;
	}
	
}
