package com.dodam.hotel.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.dto.InsertDto;
import com.dodam.hotel.dto.ManagerSignInFormDto;
import com.dodam.hotel.repository.model.Manager;

@Mapper
public interface ManagerRepository {

	public Manager findByManagernameAndPassword(ManagerSignInFormDto managerSignInFormDto);
	
	public int insert(InsertDto insertDto);
	
}
