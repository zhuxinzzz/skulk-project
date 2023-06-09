package com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class IMServer {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> cls = Class.forName("com.messageServer.WebSocketServer");
        Method mainMethod = cls.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, (Object) new String[0]);
    }
}
