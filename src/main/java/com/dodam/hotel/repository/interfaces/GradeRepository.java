package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.repository.model.GradeInfo;

@Mapper
public interface GradeRepository {
	
	// 회원가입 시 등급 부여
	public int insertGrade(Integer id);
	
	// 회원 등급 정보 조회
	public GradeInfo findGradeByUserId(Integer userId);
	
	// 회원 등급 업데이트
	public int updateUserGrade(@Param("userId") Integer userId, @Param("gradeId") Grade gradeId);
	
	// 등급 전체 조회
	public List<com.dodam.hotel.repository.model.Grade> findAllGrade();
	
}
