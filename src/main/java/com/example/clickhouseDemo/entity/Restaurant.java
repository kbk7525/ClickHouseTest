package com.example.clickhouseDemo.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("restaurant")
@Data
public class Restaurant {
    public int id;
    public String opendata_id;
    public String address;
    public String category;
    public String name;
    public String phone;
    public String hr;
    public String seat_cnt;
    public String parking;
    public String homepage;
    public String foreign;
    public String booking;
    public String play;
    public String breakfast;
    public String dessert;
    public String[] menu;
    public String description;
    public String subway;
    public String bus;
}
