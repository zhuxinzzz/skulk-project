package taskSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzz
 * @Date 02/06/2023
 */

@SpringBootApplication
//@ComponentScan(basePackages = {"taskSpringBoot.*"})
public class TaskServer {
    public static void main(String[] args) {
        SpringApplication.run(TaskServer.class, args);

    }
}
