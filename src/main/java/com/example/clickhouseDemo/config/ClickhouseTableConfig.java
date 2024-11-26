package com.example.clickhouseDemo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClickhouseTableConfig implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;
    public ClickhouseTableConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void run(ApplicationArguments args) throws Exception {
        String query = """
            create table if not exists restaurant (
                id Int32,
                opendata_id String DEFAULT '',
                address String DEFAULT '',
                category String DEFAULT '',
                name String DEFAULT '',
                phone String DEFAULT '',
                hr String DEFAULT '',
                seat_cnt String DEFAULT '',
                parking String DEFAULT '',
                homepage String DEFAULT '',
                foreign String DEFAULT '',
                booking String DEFAULT '',
                play String DEFAULT '',
                breakfast String DEFAULT '',
                dessert String DEFAULT '',
                menu Array(String),
                description String DEFAULT '',
                subway String DEFAULT '',
                bus String DEFAULT ''
            ) engine = MergeTree()
            order by id
            """;
        jdbcTemplate.execute(query);
    }
}
