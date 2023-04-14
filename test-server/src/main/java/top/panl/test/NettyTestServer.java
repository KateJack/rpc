package top.panl.test;

import top.panl.rpc.api.HelloService;
import top.panl.rpc.netty.server.NettyServer;
import top.panl.rpc.registry.DefaultServiceRegistry;
import top.panl.rpc.registry.ServiceRegistry;

/**
 * ClassName: NettyTestServer
 * Package: top.panl.test
 * Description: 测试调用Netty服务提供者（服务端）
 *
 * @Author liupan
 * @Create 2023/4/13 22:11
 * @Version 1.0
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(9999);
    }
}
