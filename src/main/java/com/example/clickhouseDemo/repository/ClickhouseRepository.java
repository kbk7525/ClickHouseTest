package com.example.clickhouseDemo.repository;

import com.example.clickhouseDemo.entity.Restaurant;
import com.example.clickhouseDemo.service.ClickhouseService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClickhouseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClickhouseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Restaurant restaurant) {
        String sql = "insert into restaurant (*) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, restaurant.getId(), restaurant.getOpendata_id(), restaurant.getAddress(),
                restaurant.getCategory(), restaurant.getName(), restaurant.getPhone(), restaurant.getHr(),
                restaurant.getSeat_cnt(), restaurant.getParking(), restaurant.getHomepage(), restaurant.getForeign(),
                restaurant.getBooking(), restaurant.getPlay(), restaurant.getBreakfast(), restaurant.getDessert(),
                restaurant.getMenu(), restaurant.getDescription(), restaurant.getSubway(), restaurant.getBus());
    }
}
