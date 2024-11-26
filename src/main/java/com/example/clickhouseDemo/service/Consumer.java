package com.example.clickhouseDemo.service;

import com.example.clickhouseDemo.entity.Restaurant;
import com.example.clickhouseDemo.repository.ClickhouseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableKafka
public class Consumer {
    private final ClickhouseRepository clickhouseRepository;

    public Consumer(ClickhouseRepository clickhouseRepository) {
        this.clickhouseRepository = clickhouseRepository;
    }

    @KafkaListener(topics = "clickhouseTest", groupId = "clickhouseTest")
    public void listen(String str) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try{
            Restaurant data = mapper.readValue(str, Restaurant.class);
            log.info("rcv msg : {}", data);
            clickhouseRepository.save(data);
        }catch (Exception ex) {
            throw new Exception("error", ex);
        }
    }
}
