package com.dodam.hotel.repository.interfaces;

import com.dodam.hotel.dto.PayDto;
import com.dodam.hotel.repository.model.Pay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayRepository {
    int insertPay(PayDto payDto);
    Pay findByTidPay(String tid);
}
