package com.dodam.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.repository.interfaces.CouponRepository;
import com.dodam.hotel.repository.model.Coupon;
import com.dodam.hotel.util.PagingObj;

@Service
public class CouponService {
	
	@Autowired
	private CouponRepository couponRepository;
	
	// 특정 유저가 소유한 쿠폰 조회
	@Transactional
	public List<Coupon> readByUserId(Integer userId) {
		List<Coupon> couponEntity = couponRepository.findByUserId(userId);
		return couponEntity;
	}
	
	// 특정 유저가 소유한 쿠폰 개수
	@Transactional
	public int readCouponCount(Integer userId) {
		int resultCount = couponRepository.findCouponCountByUserId(userId);
		return resultCount;
	}
	
	// 특정 유저가 소유한 쿠폰 조회 (페이징)
	public List<Coupon> readCouponByUserId(PagingObj obj, Integer userId) {
		List<Coupon> couponEntitys = couponRepository.findByUserIdPaging(obj, userId);
		return couponEntitys;
	}
	
} // end of class
