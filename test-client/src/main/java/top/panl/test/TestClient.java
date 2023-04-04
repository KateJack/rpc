package top.panl.test;

import top.panl.rpc.api.HelloObject;
import top.panl.rpc.api.HelloService;
import top.panl.rpc.client.RpcClientProxy;

/**
 * ClassName: TestClient
 * Package: top.panl.test
 * Description: 测试用消费者（客户端）
 *
 * @Author liupan
 * @Create 2023/3/30 20:09
 * @Version 1.0
 */
public class TestClient {

    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1",9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12,"This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
