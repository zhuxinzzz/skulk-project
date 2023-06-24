package org.pojo;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class MessageLine {
    String flagLine;
    char flag = '~';
    String date;
    String toUserId;
    String fromUserId;
    String msg;

    @Override
    public String toString() {
        return flagLine + '\n'
                + msg;
    }

    public MessageLine(String date, String toUserId, String fromUserId, String content) {
        flagLine = flag + date + toUserId + fromUserId;
        this.date = date;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.msg = content;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
