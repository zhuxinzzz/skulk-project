import consumer.TaskApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zzz
 * @Date 08/06/2023
 */
@SpringBootTest(classes = TaskApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {
    @Test
    public void func() {
        System.out.println("time");
    }

}
