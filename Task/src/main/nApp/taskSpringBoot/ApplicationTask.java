package taskSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zzz
 * @Date 02/06/2023
 */

@SpringBootApplication
//@ComponentScan(basePackages = {"taskSpringBoot.*"})
public class ApplicationTask {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTask.class, args);

    }
}
