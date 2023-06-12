package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.dto.InquiryRequestDto;
import com.dodam.hotel.dto.UserRequestDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.repository.model.User;

@Mapper
public interface UserRepository {
	

	// 로그인용 select
	public UserResponseDto.LoginResponseDto findUserByLoginFormDto(UserRequestDto.LoginFormDto user);

	// update용
	public int updateUserByEmail(UserRequestDto.MyPageFormDto user);
	
	// 회원 탈퇴
	public int deleteUser(User user);
	
	// 카카오 로그인 유저 찾기
	public UserResponseDto.LoginResponseDto findUserKakao(String email);
	
	// 특정 유저 정보 찾기
	public User findUserByEmail(String email);
	
	// 회원가입
	public int insert(UserRequestDto.InsertDto insertDto);
	
	// 카카오 회원가입
	public int insertKakao(UserRequestDto.InsertDto insertDto);
	
	// 제일 최근 가입 회원 ID 찾기
	public Integer findIdOrderById(UserRequestDto.InsertDto insertDto);
	
	// 오늘 가입 회원 조회
	public List<User> findUserToday();
	
	// 회원 id 조회
	public User findUserForIdInquiry(InquiryRequestDto.IdInquiryRequestDto idInquiryRequestDto);
	
	// 임시 pw update 처리
	public int updatePwByUserInfo(InquiryRequestDto.PwInquiryRequestDto pwInquiryRequestDto);
	
	// 비밀번호 변경 페이지에서 비밀번호 변경 처리
	public int updateOnlyPw(@Param("password") String password, @Param("userId") Integer userId);
	
	// 임시 비밀번호 발급 상태 변경
	public int updatePwdStatus(Integer userId);
	
	// 재가입 유저 조회용
	public User findUserByOriginEmail(String email);
	
	// 재가입 유저 회원가입용
	public int updateUserByOriginEmail(UserRequestDto.InsertDto insertDto);
	
}
