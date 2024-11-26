package com.example.clickhouseDemo.service;

import com.example.clickhouseDemo.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableKafka
public class Producer {
    @Value("${kafka.topic.name}")
    private String topic;
    private final KafkaTemplate<String, Restaurant> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, Restaurant> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Restaurant data) {
        this.kafkaTemplate.send(topic, data);
        log.info("send msg : " + data);
    }
}
