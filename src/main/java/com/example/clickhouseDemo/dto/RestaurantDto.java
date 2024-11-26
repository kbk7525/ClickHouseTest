package com.example.clickhouseDemo.dto;

import com.example.clickhouseDemo.entity.Restaurant;
import com.example.clickhouseDemo.service.ClickhouseService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestaurantDto {

    @JsonProperty("cnt")
    public int id;
    @JsonProperty("OPENDATA_ID")
    public String opendata_id;
    @JsonProperty("GNG_CS")
    public String address;
    @JsonProperty("FD_CS")
    public String category;
    @JsonProperty("BZ_NM")
    public String name;
    @JsonProperty("TLNO")
    public String phone;
    @JsonProperty("MBZ_HR")
    public String hr;
    @JsonProperty("SEAT_CNT")
    public String seat_cnt;
    @JsonProperty("PKPL")
    public String parking;
    @JsonProperty("HP")
    public String homepage;
    @JsonProperty("PSB_FRN")
    public String foreign;
    @JsonProperty("BKN_YN")
    public String booking;
    @JsonProperty("INFN_FCL")
    public String play;
    @JsonProperty("BRFT_YN")
    public String breakfast;
    @JsonProperty("DSSRT_YN")
    public String dessert;
    @JsonProperty("MNU")
    public String menu;
    public String[] menu_array;
    @JsonProperty("SMPL_DESC")
    public String description;
    @JsonProperty("SBW")
    public String subway;
    @JsonProperty("BUS")
    public String bus;

    public Restaurant convertToEntity() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(this.id);
        restaurant.setOpendata_id(this.opendata_id);
        restaurant.setAddress(this.address);
        restaurant.setCategory(this.category);
        restaurant.setName(this.name);
        restaurant.setPhone(this.phone);
        restaurant.setHr(this.hr);
        restaurant.setSeat_cnt(this.seat_cnt);
        restaurant.setParking(this.parking);
        restaurant.setHomepage(this.homepage);
        restaurant.setForeign(this.foreign);
        restaurant.setBooking(this.booking);
        restaurant.setPlay(this.play);
        restaurant.setBreakfast(this.breakfast);
        restaurant.setDessert(this.dessert);
        restaurant.setMenu(this.menu_array);
        restaurant.setDescription(this.description);
        restaurant.setSubway(this.subway);
        restaurant.setBus(this.bus);
        return restaurant;
    }
}
