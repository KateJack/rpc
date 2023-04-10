package top.panl.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.registry.ServiceRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * ClassName: RpcServer
 * Package: top.panl.rpc.server
 * Description: 远程方法调用的提供者（服务端）
 *
 * @Author liupan
 * @Create 2023/3/30 19:44
 * @Version 1.0
 */
public class RpcServer {


    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);


    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;


    public RpcServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE_TIME,TimeUnit.SECONDS,workingQueue,threadFactory);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器启动......");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接：{}:{}",socket.getInetAddress(),socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler,serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.info("服务器启动时有错误发生：",e);
        }
    }

}
