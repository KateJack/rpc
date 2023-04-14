package top.panl.test;

import top.panl.rpc.api.HelloService;
import top.panl.rpc.registry.DefaultServiceRegistry;
import top.panl.rpc.registry.ServiceRegistry;
import top.panl.rpc.socket.server.SocketServer;

/**
 * ClassName: TestServer
 * Package: top.panl.test
 * Description: 测试用服务提供方（服务端）
 *
 * @Author liupan
 * @Create 2023/3/30 20:16
 * @Version 1.0
 */
public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }
}
