/**
 * @author zzz
 * @Date 26/05/2023
 */
public class Task {
    public void saveMessageFileToRemoteFS(String fileName,byte[] centent) {
    }

    public void AppendContentToTheMessageFileOfTheRemoteMachine(String fileName,String content) {
        SshConnect SSHConnect = new SshConnect();
        SSHConnect.RemoteFileAppender(fileName, content);
    }
}
