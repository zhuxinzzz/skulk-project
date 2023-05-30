import jdbc.mysql.MessageStoreDB;

/**
 * @author zzz
 * @Date 26/05/2023
 */
public class Task {
    public void saveMessageFileToRemoteFS(String fileName, byte[] centent) {
    }

    /*可以追加，也可以创建文件。*/
    public void AppendContentToTheMessageFileOfTheRemoteMachine(String fileName, String content) {
//        写远程机器的文件系统
        SshConnect SSHConnect = new SshConnect();
        SSHConnect.RemoteFileAppender(fileName, content);

    }

    public void saveFileMetaInfoToMysql(String fileName, String path) {
        //        写机器的mysql
        MessageStoreDB messageStoreDB = new MessageStoreDB();
        messageStoreDB.insertRow(fileName, path, "0");
    }
}
