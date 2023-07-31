package com.ssafy.a403.rabbitmq;

import com.ssafy.a403.global.util.rabbitmq.SttProducer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SttProducerTest {

    @Autowired
    SttProducer sttProducer;
    @Autowired RabbitTemplate rabbitTemplate;

    @Test
    public void produceSttTaskTest() throws Exception {
        //given
        String testId = "testId";
        String testPath = "src/main/resources/static/saju1.mp3";

        //when
        sttProducer.produceSttTask(testId, testPath);

        //then
        //수신은 수신서버 측에서 확인

    }
}