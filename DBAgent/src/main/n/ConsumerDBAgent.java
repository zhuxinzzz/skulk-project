package comApp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @Date 08/06/2023
 */
@Component
public class ConsumerDBAgent {
    @Autowired
    KafkaTemplate kafkaTemplate;

    String respondToUserOfflineInformationRecords() {
        kafkaTemplate.send("respondToUserOfflineInformationRecords","aaa");
        return null;
    }

    @KafkaListener(topics = "requestUserOfflineMessageRecord")
    String func(ConsumerRecord<?, String> record) {
        System.out.println(record.value());
        return null;
    }



}
