package top.panl.test;

import top.panl.rpc.RpcClient;
import top.panl.rpc.RpcClientProxy;
import top.panl.rpc.api.HelloObject;
import top.panl.rpc.api.HelloService;
import top.panl.rpc.netty.client.NettyClient;

/**
 * ClassName: NettyTestClient
 * Package: top.panl.test
 * Description: 测试用Netty消费
 *
 * @Author liupan
 * @Create 2023/4/13 22:27
 * @Version 1.0
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1",9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12,"This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
