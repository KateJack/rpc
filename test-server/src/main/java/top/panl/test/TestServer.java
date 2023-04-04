package top.panl.test;

import top.panl.rpc.api.HelloService;
import top.panl.rpc.server.RpcServer;

/**
 * ClassName: TestServer
 * Package: top.panl.test
 * Description: 测试用服务提供方（服务端）
 *
 * @Author liupan
 * @Create 2023/3/30 20:16
 * @Version 1.0
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
