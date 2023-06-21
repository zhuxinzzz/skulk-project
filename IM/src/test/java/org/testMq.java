package org;

import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testMq {
    MqIM mqim = new MqIM();

    @Test
    public void test() {
        mqim.saveOfflineMessages("aaa.txt", "line1\nline2");
    }
}
