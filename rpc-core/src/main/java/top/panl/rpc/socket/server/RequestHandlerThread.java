package top.panl.rpc.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.RequestHandler;
import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.entity.RpcResponse;
import top.panl.rpc.registry.ServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ClassName: ResquestHandlerThread
 * Package: top.panl.rpc.socket.server
 * Description: 处理RpcRequest的工作线程
 *
 * @Author liupan
 * @Create 2023/4/10 21:41
 * @Version 1.0
 */
public class RequestHandlerThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(rpcRequest,service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.info("调用或发送时有错误发生：",e);
        }
    }
}
