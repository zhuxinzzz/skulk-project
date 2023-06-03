package task.tmp;

import com.jcraft.jsch.*;

import java.io.*;

public class LargeFileUploader {

    public static void main(String[] args) {
        String host = "remote.host";
        String username = "username";
        String password = "password";
        String localFilePath = "/path/to/local/file.txt";
        String remoteFilePath = "/path/to/remote/file.txt";
        int blockSize = 1024 * 1024; // 1MB

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();

            InputStream inputStream = new FileInputStream(new File(localFilePath));
            byte[] buffer = new byte[blockSize];
            int bytesRead = -1;
            int blockNumber = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String remoteBlockFilePath = remoteFilePath + ".part" + blockNumber;
                sftpChannel.put(new ByteArrayInputStream(buffer, 0, bytesRead), remoteBlockFilePath);
                blockNumber++;
            }

            inputStream.close();

            // 合并分块文件
            sftpChannel.put(new ByteArrayInputStream(new byte[0]), remoteFilePath);
            for (int i = 0; i < blockNumber; i++) {
                String remoteBlockFilePath = remoteFilePath + ".part" + i;
                sftpChannel.rename(remoteBlockFilePath, remoteBlockFilePath + ".done");
                sftpChannel.put(remoteBlockFilePath + ".done", remoteFilePath, ChannelSftp.APPEND);
                sftpChannel.rm(remoteBlockFilePath + ".done");
            }

            sftpChannel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }
}