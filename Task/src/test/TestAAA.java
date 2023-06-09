import jdbc.mysql.MessageStoreDB;
import org.junit.Test;
import taskSpringBoot.entity.Task;

import java.util.Arrays;
import java.util.Random;

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
        messageStoreDB.insertRow(fileName+ new Random().nextInt(999),"/home/ubuntu/1"+fileName,"0");

    }
@Test
    public void testStringSplite() {
        String value = "file1-contentcontentcontentcontentcontent";
        String[] split = value.split("-");
        System.out.println(Arrays.deepToString(split));
    for (String s : value.split("-")) {
        System.out.println(s);
    }

        String fileName = split[0];
        String contents = split[1];
        System.out.println(fileName+"\t"+contents);

    }

}
