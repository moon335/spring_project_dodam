package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.enums.CouponInfo;
import com.dodam.hotel.repository.model.Coupon;
import com.dodam.hotel.util.PagingObj;

/**
 * 
 * @author 성희, 현우
 * 쿠폰 repository
 */
@Mapper
public interface CouponRepository {
	
	// 쿠폰 등록
	public int insert(@Param("couponInfoId") CouponInfo couponInfoId, @Param("userId") Integer userId);
	
	// 특정 회원 쿠폰 조회 (안쓸듯?)
	public List<Coupon> findByUserId(Integer userId);
	
	// 특정 회원 모든 쿠폰 개수 조회
	public int findCouponCountByUserId(Integer userId);
	
	// 특정 회원 쿠폰 조회(페이징)
	public List<Coupon> findByUserIdPaging(@Param("obj") PagingObj obj, @Param("userId") Integer userId);

	int deleteByUserIdandCouponInfoId(@Param("userId")Integer userId, @Param("couponId")Integer couponId);
	
}
