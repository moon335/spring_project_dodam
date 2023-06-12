package com.dodam.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.repository.model.Coupon;
import com.dodam.hotel.service.CouponService;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;

@Controller
public class CouponController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/couponList")
	public String couponList(Model model, @RequestParam(name = "nowPage", defaultValue = "1" , required = false) String nowPage, @RequestParam(name = "cntPerPage", defaultValue = "1" , required = false) String cntPerPage) {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		int total = couponService.readCouponCount(principal.getId());
		
		PagingObj po = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<Coupon> responseCoupons = couponService.readCouponByUserId(po, principal.getId());
		model.addAttribute("paging", po);
		if(responseCoupons.size() == 0) {
			model.addAttribute("coupons", null);
		} else {
			model.addAttribute("coupons", responseCoupons);
		}
		return "/user/couponList";
	}
}
