import org.junit.Assert;
import org.junit.Test;
import org.l2Service.serviceImlp.UserOfflineMessageServiceImpl;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testServiceImpl {
    @Test
    public void test() {
        UserOfflineMessageServiceImpl userOfflineMessageServiceImpl = new UserOfflineMessageServiceImpl();
        String userOfflineMessageMessageRecord = userOfflineMessageServiceImpl.getUserOfflineMessageMessageRecord("user1");
        Assert.assertEquals("value1", userOfflineMessageMessageRecord);
    }

}
