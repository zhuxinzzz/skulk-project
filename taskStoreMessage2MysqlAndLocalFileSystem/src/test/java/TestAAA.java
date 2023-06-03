
import jdbc.mysql.MessageStoreDB;
import org.junit.jupiter.api.Test;


/**
 * @author zzz
 * @Date 29/05/2023
 */
public class TestAAA {
    static String fileName = "13112312312" + "+" + "13112312313" + ".bak";
    static String content = "2023年05月12日22:20:43|1|2|ok？\n" +
            "2023年05月12日22:25:43|2|1|ok\n2023年05月12日22:26:43|2|1|ok\n";

    public void AppendContentToTheMessageFileOfTheRemoteMachine(String[] args) {
        Task task = new Task();
        task.AppendContentToTheMessageFileOfTheRemoteMachine(
                "13112312312" + "+" + "13112312313" + ".bak",
                "2023年05月12日22:20:43|1|2|ok？\n2023年05月12日22:25:43|2|1|ok\n2023年05月12日22:26:43|2|1|ok\n");
    }
//@Test
    public void testWriteMysql() {
        Task task = new Task();
        task.saveFileMetaInfoToMysql(fileName, "aaa");
    }


    @Test
    public void insertRow() {

        MessageStoreDB messageStoreDB = new MessageStoreDB();
//        String md5 = messageStoreDB.getMD5(content);
//        System.out.println(md5);
        messageStoreDB.insertRow(fileName+"8","/home/ubuntu/1"+fileName,"0");

    }


}
