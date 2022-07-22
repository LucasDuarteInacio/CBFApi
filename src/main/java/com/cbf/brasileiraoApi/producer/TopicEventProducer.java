package com.cbf.brasileiraoApi.producer;


import com.cbf.brasileiraoApi.entity.Event;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicEventProducer {

    @Value("${spring.kafka.producer.topicName}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key , Event event){
        final Gson gson = new Gson();
        final String message = gson.toJson(event);
        final var record =  new ProducerRecord<>(topicName,key, message);
        kafkaTemplate.send(record);
    }
}
