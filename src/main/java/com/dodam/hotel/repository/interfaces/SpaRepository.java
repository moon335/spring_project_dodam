package com.dodam.hotel.repository.interfaces;

import java.util.List;

import com.dodam.hotel.dto.FacilitiesRequestDto;
import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.repository.model.Spa;

/**
 * @author lhs-devloper
 */
@Mapper
public interface SpaRepository {
    List<Spa> findAllSpa();
    int updateSpaStatus(FacilitiesRequestDto facilitiesRequestDto);
}
