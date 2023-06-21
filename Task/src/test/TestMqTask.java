import org.RpcTask;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class TestMqTask {
    @Test
    public void testDate() {
//        long time = new Date().getTime();
//        System.out.println(time);

        // 创建 SimpleDateFormat 对象并设置日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE-MMM-dd-HH:mm:ss-zzz-yyyy");

        String formattedDate = dateFormat.format(new Date());
        System.out.println("Formatted date: " + formattedDate);
        /*Tue-Jun-13-10:56:36-CST-2023*/
    }
    @Test
    public void test() {
        RpcTask rpcTask = new RpcTask();
        String fileName = rpcTask.getUserOfflineMessageStorageFile("user1");
        System.out.println(fileName);
        Assert.assertEquals("user1+user2.bak", fileName);
    }

}
