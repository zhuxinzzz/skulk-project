package org;

import com.google.gson.Gson;
import org.junit.Test;
import org.messageServer.pojo.ClientMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testMessageForwarding {
    private static final Gson GSON = new Gson();

    @Test
    public void testMqProduce() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE-MMM-dd-HH:mm:ss-zzz-yyyy");
        /*"Tue-Jun-13-10:56:36-CST-2023"*/
        String formattedDate = dateFormat.format(new Date());

        ClientMessage clientMessage = new ClientMessage(
                formattedDate, "user2", "user1",
                "line1line2");
        String cliMessage = GSON.toJson(clientMessage);

//        productionWritesChatFileMessages1(clientMessage.getFromUserId(), cliMessage);
//        System.out.println("produced");

        while (true) {
            RpcIM rpcIM = new RpcIM();
            rpcIM.productionWritesChatFileMessages1("didn't work", cliMessage);
            System.out.println(cliMessage);
            System.out.println("produced");
            Thread.sleep(1000);
//            String s = new Scanner(System.in).nextLine();
        }
    }

}
