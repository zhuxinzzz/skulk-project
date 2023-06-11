package taskSpringBoot.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @Date 08/06/2023
 */
@Component
public class Producer {
        @Autowired
        KafkaTemplate<String, String> kafkaTemplate;

        void send() {
                kafkaTemplate.send("requestUserOfflineMessageRecord", "Services");

        }

//        @KafkaListener(topics = "")
//        String getOfflineRecords() {
//                return null;
//        }

}
//@RestController
//public class Producer {
//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
//
//    @RequestMapping("/send")
//    public String send(String fileName, String content) {
//        kafkaTemplate.send("storeFileToFs", fileName+"-"+content);
//
//        return fileName;
//    }