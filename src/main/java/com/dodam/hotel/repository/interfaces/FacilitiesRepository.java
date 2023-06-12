package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.repository.model.AllFacilities;
import com.dodam.hotel.repository.model.Fitness;
import com.dodam.hotel.repository.model.Pool;
import com.dodam.hotel.repository.model.Spa;

/**
 * 
 * @author 성희 부대시설 repository
 */
@Mapper
public interface FacilitiesRepository {

	// 수영장 정보 조회
	public Pool findPool();
	public List<Pool> findByAllPool();

	// 스파 정보 조회
	public Spa findSpa();
	public List<Spa> findByAllSpa();

	// 피트니스 정보 조회
	public Fitness findFitness();

	public List<Fitness> findByAllFitness();

	
	// 부대시설 상태 조회
	public int findPoolStatus();
	public int findSpaStatus();
	public int findFitnessStatus();

	// 부대시설 전체 조회 (매니저)
	public List<AllFacilities> findAllFacilities();
}
