package task.tmp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Properties;
import java.util.Vector;

public class SshExample {
    public static void main(String[] args) throws Exception {
        JSch jsch = new JSch();

        // 设置登录用户名和密码
        String user = "root";
        String password = "Qwe123";
//        String host = "remote.host.com";
        String host = "s1";

        // 创建Session连接
        Session session = jsch.getSession(user, host, 2333);
        session.setPassword(password);

        // 设置第一次登录时提示，可选值：yes/no/ask
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        // 创建SftpChannel通道
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();

        // 列出指定目录下的文件和文件夹
        String remoteDir = "/opt";
        Vector<ChannelSftp.LsEntry> fileList = channel.ls(remoteDir);
        for (ChannelSftp.LsEntry entry : fileList) {
            System.out.println(entry.getFilename());
        }

        // 关闭连接
        channel.disconnect();
        session.disconnect();
    }
}