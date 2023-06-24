import org.RpcTask;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testRpcTask {
    RpcTask rpcTask = new RpcTask();

    @Test
    public void testReleaseToObtainOfflineDataInterface() {

        rpcTask.publishAServiceForGettingOfflineMessages();
    }

    @Test
    public void testUsingRpcToGetTheFileNameService() {
        String fileName = rpcTask.getUserOfflineMessageStorageFile("user1");
        System.out.println(fileName);
        Assert.assertEquals("user1+user2.bak", fileName);
    }

}
