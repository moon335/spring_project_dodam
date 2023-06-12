package com.dodam.hotel.repository.interfaces;

import java.util.List;

import com.dodam.hotel.dto.FacilitiesRequestDto;
import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.repository.model.Fitness;

/**
 * @author lhs-devloper
 */
@Mapper
public interface FitnessRepository {
    List<Fitness> findAllFitness();
    int updateFitnessStatus(FacilitiesRequestDto facilitiesRequestDto);
}
