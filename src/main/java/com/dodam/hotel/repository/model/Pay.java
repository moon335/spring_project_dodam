package com.dodam.hotel.repository.model;

import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.enums.PGType;
import lombok.Data;

import java.sql.Timestamp;
import java.text.DecimalFormat;

@Data
public class Pay {
    private Integer id;
    private String payTid;
    private Integer price;
    private Timestamp createdAt;
    private PGType pgType;
    private String gradeName;
    
    public String formatPrice() {
  		DecimalFormat df = new DecimalFormat("###,###");
  		String formatNumber = df.format(price);
  		return formatNumber;
  	}
}
