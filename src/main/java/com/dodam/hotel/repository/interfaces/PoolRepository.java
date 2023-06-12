package com.dodam.hotel.repository.interfaces;

import java.util.List;

import com.dodam.hotel.dto.FacilitiesRequestDto;
import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.repository.model.Pool;

/**
 * @author lhs-devloper
 */
@Mapper
public interface PoolRepository {
    List<Pool> findAllPool();

    int updatePoolStatus(FacilitiesRequestDto facilitiesRequestDto);
}
