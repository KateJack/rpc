package top.panl.rpc.socket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.RpcClient;
import top.panl.rpc.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ClassName: RpcClient
 * Package: top.panl.rpc.socket.client
 * Description: Socket方式远程方法调用的消费者（客户端）
 *
 * @Author liupan
 * @Create 2023/3/30 19:20
 * @Version 1.0
 */
public class SocketClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private final String host;
    private final int port;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        try(Socket socket = new Socket(host,port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：", e);
            return null;
        }
    }
}
