package producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzz
 * @Date 05/06/2023
 */
@RestController
public class Producer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public String send(String fileName, String content) {
        kafkaTemplate.send("storeFileToFs", fileName+"-"+content);

        return fileName;
    }

}
