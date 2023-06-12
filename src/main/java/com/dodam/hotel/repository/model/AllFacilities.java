package com.dodam.hotel.repository.model;

import lombok.Data;


@Data
public class AllFacilities {
    private Integer id;
    private String name;
    private String location;
    private Pool pool;
    private Spa spa;
    private Fitness fitness;
}
