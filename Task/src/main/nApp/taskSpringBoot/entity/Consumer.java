package taskSpringBoot.entity;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @Date 05/06/2023
 */
@Component
public class Consumer {
    @Autowired
    Task task;

    @KafkaListener(topics = "storeFileToFs")
    public void saveChatHistoryFile(ConsumerRecord<?, String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);

        String[] split = value.split("-");
//        System.out.println(split);
        String fileName = split[0];
        String contents = split[1];
        System.out.println(fileName+"\t"+contents);

        task.AppendContentToTheMessageFileOfTheRemoteMachine(fileName, contents+"\n");
    }

    void getOfflineMessages() {

    }
}
