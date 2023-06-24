package chatServer.messageServer;

import lombok.Data;

@Data
public class ClientMessage {
    private String toUserId;
//    private String toUserName;
    private String fromUserId;
//    private String fromUserName;
    private String msg;

    public ClientMessage(String toUserId, String fromUserId, String msg) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.msg = msg;
    }

//    private String currentDate;
//    private int messageType;


//    public ClientMessage(String fromUserId,
//                         String fromUserName,
//                         String toUserId,
//                         String toUserName,
//                         String msg,
//                         int messageType) {
//        this.fromUserId = fromUserId;
//        this.fromUserName = fromUserName;
//        this.toUserId = toUserId;
//        this.toUserName = toUserName;
//        this.msg = msg;
//        this.currentDate = LocalDateTime.now().toString();
//        this.messageType = messageType;
//    }
}