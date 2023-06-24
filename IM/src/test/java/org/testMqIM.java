package org;

import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testMqIM {
    MqIM mqim = new MqIM();

    @Test
    public void testStoringMessagesToTheFileSystem() {
        mqim.saveOfflineMessages("aaa.txt", "line1\nline2");
    }
}
