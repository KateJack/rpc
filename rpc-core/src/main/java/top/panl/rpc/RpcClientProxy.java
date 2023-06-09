package top.panl.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.entity.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName: RpcClientProxy
 * Package: top.panl.rpc.socket.client
 * Description: RPC客户端动态代理
 *
 * @Author liupan
 * @Create 2023/3/30 19:33
 * @Version 1.0
 */
public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);


    private final RpcClient client;

    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法：{}#{}",method.getDeclaringClass().getName(),method.getName());
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(),
                method.getName(),args,method.getParameterTypes());
        return client.sendRequest(rpcRequest);

    }
}
