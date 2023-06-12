package com.dodam.hotel.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.repository.model.Point;

/**
 * 
 * @author 성희 point repository
 */
@Mapper
public interface PointRepository {

	// 포인트 회원가입 시 자동 세팅
	public int insertZeroPoint(Integer id);

	// 특정 회원 포인트 조회
	public Integer findByUserId(Integer userId);

	// 포인트 적립 & 사용
	int insertPoint(@Param("point") Integer point, @Param("userId") Integer userId);

}
