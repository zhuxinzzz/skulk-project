package org;

/**
 * @author zzz
 * @Date 09/06/2023
 *
 * Publish services that operate database and storage middleware。
 */
public class DBAgentServer {
    public static void main(String[] args) {
        RpcDBAgent rpcDBAgent = new RpcDBAgent();
        rpcDBAgent.publishAllServices();
    }
}
