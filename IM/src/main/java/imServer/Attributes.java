package imServer;

import io.netty.util.AttributeKey;
import imServer.entity.Session;

public interface Attributes {

    //登录成功标志，会带在每一个登录成功的channel上
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    //登录成功后把当前channel的用户信息跟channel绑定
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
