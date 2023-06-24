package org.game.skulk.api.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author zzz
 * @Date 12/06/2023
 */
public class DubboConfig {
    public static ApplicationConfig applicationConfig = new ApplicationConfig();
    /*注册中心配置*/
    public static RegistryConfig registryConfig = new RegistryConfig();
    /*协议配置*/
    public static ProtocolConfig protocolConfig = new ProtocolConfig();
    static {
        /**/
        applicationConfig.setName("dubbo-api-Task-providerAndConsumer");
        /**/
        registryConfig.setAddress("zookeeper://s1:2181");
        /**/
        protocolConfig.setName("dubbo");
    }

}
