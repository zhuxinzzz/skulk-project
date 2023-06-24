import org.MqTask;
import org.RpcTask;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testMqTask {
    MqTask mqTask = new MqTask();

    @Test
    public void 测试保存离线消息() {
        mqTask.startTheConsumerToWriteTextToTheFilesystem();
    }
    @Test
    public void test() {
        RpcTask rpcTask = new RpcTask();
        String fileName = rpcTask.getUserOfflineMessageStorageFile("user1");
        System.out.println(fileName);
        Assert.assertEquals("user1|user2.bak", fileName);
    }

    @Test
    public void testConsumeMQ() {
        mqTask.consumerMessagesAreWrittenToTheFileSystem1();
    }


}
