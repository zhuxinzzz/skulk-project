package org.messageServer.pojo;


import lombok.ToString;

public class ClientMessage {
    private String date;
    private String toUserId;
//    private String toUserName;
    private String fromUserId;
//    private String fromUserName;
    private String content;


    public String toString() {
        return "ClientMessage{" +
                "date='" + date + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClientMessage(String date, String toUserId, String fromUserId, String content) {
        this.date = date;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = content;
    }

    public ClientMessage(String toUserId, String fromUserId, String content) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = content;
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