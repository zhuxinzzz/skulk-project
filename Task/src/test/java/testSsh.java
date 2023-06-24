import org.junit.Assert;
import org.junit.Test;
import org.l9.SshConnect;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testSsh {
    SshConnect sshConnect = new SshConnect();
    @Test
    public void testRemoteFileAppender() {
        String path = "/opt/skulk/";
        String fileName = "user1+user2.bak";
        int result = sshConnect.RemoteFileAppender(
                fileName,
                "add line98\nadd line99");
        // 断言返回值是否为零
        Assert.assertEquals(0, result);
    }


}
