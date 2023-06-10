package org.entity;

import org.l9.jdbc.mysql.MessageStoreDB;

/**
 * @author zzz
 * @Date 26/05/2023
 */
public class Task {
    public void saveMessageFileToRemoteFS(String fileName, byte[] centent) {
    }

    /*可以追加，也可以创建文件。*/
    //        写远程机器的文件系统
    public static void AppendContentToTheMessageFileOfTheRemoteMachine(String fileName, String content) {
        SshConnect SSHConnect = new SshConnect();
        SSHConnect.RemoteFileAppender(fileName, content);

    }

    public void saveFileMetaInfoToMysql(String fileName, String path) {
        //        写机器的mysql
        MessageStoreDB messageStoreDB = new MessageStoreDB();
        messageStoreDB.insertRow(fileName, path, "0");
    }

    public static void main(String[] args) {
        Task task = new Task();
        task.saveFileMetaInfoToMysql("file2", "");
    }
}
