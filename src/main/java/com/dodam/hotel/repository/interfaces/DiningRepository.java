package com.dodam.hotel.repository.interfaces;


import java.util.List;


import com.dodam.hotel.dto.FacilitiesRequestDto;
import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.DiningDesc;


/**
 * @author lhs-devloper
 */
@Mapper
public interface DiningRepository {
    List<Dining> findAllDining();
    public List<DiningDesc> findByTypeAllDining(String type);
    
    // 레스토랑 상태 확인
    public int findStatusRestaurant();
    
    // 라운지&바 상태 확인
    public int findStatusBar();
    
    public Dining findDiningById(Integer id);

    int updateDiningStatus(FacilitiesRequestDto facilitiesRequestDto);
}
