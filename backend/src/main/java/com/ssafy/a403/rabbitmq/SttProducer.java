package com.ssafy.a403.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SttProducer {

    private static final String QUEUE_NAME = "stt_queue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Queue createQueue() {
        return new Queue(QUEUE_NAME);
    }

    public void produceSttTask(String reservationId, String audioPath) throws JsonProcessingException {

        final SttRequest sttRequest = SttRequest.builder()
                .reservationId(reservationId)
                .audioPath(audioPath)
                .build();

        String jsonSttRequest = objectMapper.writeValueAsString(sttRequest);
        rabbitTemplate.convertAndSend(QUEUE_NAME, jsonSttRequest);
        System.out.println("메시지를 보냈습니다: " + jsonSttRequest);
    }
}
