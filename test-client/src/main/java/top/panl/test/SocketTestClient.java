package top.panl.test;

import top.panl.rpc.api.HelloObject;
import top.panl.rpc.api.HelloService;
import top.panl.rpc.RpcClientProxy;
import top.panl.rpc.socket.client.SocketClient;

/**
 * ClassName: TestClient
 * Package: top.panl.test
 * Description: 测试用消费者（客户端）
 *
 * @Author liupan
 * @Create 2023/3/30 20:09
 * @Version 1.0
 */
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
