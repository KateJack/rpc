package top.panl.rpc.netty.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.RequestHandler;
import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.entity.RpcResponse;
import top.panl.rpc.registry.DefaultServiceRegistry;
import top.panl.rpc.registry.ServiceRegistry;


/**
 * ClassName: NettyServerHandler
 * Package: top.panl.rpc.netty.server
 * Description: Netty中处理RpcRequest的handler
 *
 * @Author liupan
 * @Create 2023/4/13 21:44
 * @Version 1.0
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    private static RequestHandler requestHandler;
    private static ServiceRegistry serviceRegistry;

    static {
        requestHandler = new RequestHandler();
        serviceRegistry = new DefaultServiceRegistry();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        try {
            logger.info("服务器收到请求：{}",msg);
            String interfaceName = msg.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(msg,service);
            ChannelFuture future = ctx.writeAndFlush(RpcResponse.success(result));
            future.addListener(ChannelFutureListener.CLOSE);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("处理过程调用时有错误发生：");
        cause.printStackTrace();
        ctx.close();
    }
}
