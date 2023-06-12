package com.dodam.hotel.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FacilitiesRequestDto {
    private String target;
    private String name;
    private String numberOfP;
    private Boolean availability;
    private String description;
}
