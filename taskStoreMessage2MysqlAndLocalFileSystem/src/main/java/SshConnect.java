import com.jcraft.jsch.*;

import java.io.*;

/**
 * @author zzz
 * @Date 26/05/2023
 */
public class SshConnect {
    static String host = "s1";
    static String username = "ubuntu";
    static String password = "Qwe123";
    static JSch jsch = new JSch();
    static Session session;

    static {
        try {
            session = jsch.getSession(username, host, 2333);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
    public void testRemoteFileAppender() {
        String path = "/opt/skulk/";
        String fileName = "test.txt";
        int result = RemoteFileAppender(path + fileName,
                "add line1\nadd line2");
        // 断言返回值是否为零
//        Assert.assertEquals(0, result);
    }
/*对远程机器指定路径下文件追加行。文件不存在则创建。*/
    public int RemoteFileAppender(String remoteFilePath, String content) {
        try {
            session.connect();
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();

            OutputStream out = sftpChannel.put(remoteFilePath, ChannelSftp.APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(content);
//            writer.newLine();
            writer.close();

            sftpChannel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0;
    }

//    public void RemoteFileUploader(String[] args) {
//        String localFilePath = "/path/to/local/file.txt";
//        String remoteFilePath = "/path/to/remote/file.txt";
//
//        try {
//
//            Session session = jsch.getSession(username, host, 22);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//
//            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//            sftpChannel.connect();
//
//            sftpChannel.put(localFilePath, remoteFilePath);
//
//            sftpChannel.disconnect();
//            session.disconnect();
//        } catch (JSchException | SftpException e) {
//            e.printStackTrace();
//        }
//    }

}

