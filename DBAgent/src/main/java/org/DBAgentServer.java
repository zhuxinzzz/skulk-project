package org;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class DBAgentServer {
    public static void main(String[] args) {
        RpcDBAgent rpcDBAgent = new RpcDBAgent();
        rpcDBAgent.publishAllServices();
    }


}
