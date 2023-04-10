package top.panl.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.entity.RpcResponse;
import top.panl.rpc.enumeration.ResponseCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * ClassName: ResquestHandler
 * Package: top.panl.rpc.server
 * Description: 实际进行过程调用的工作线程
 * 进行过程调用的处理器
 *
 * @Author liupan
 * @Create 2023/4/10 21:12
 * @Version 1.0
 */
public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public Object handle(RpcRequest rpcRequest, Object service) {
        Object result = null;
        try {
            result = invokeTargetMethod(rpcRequest,service);
            logger.info("服务：{} 成功调用方法：{}",rpcRequest.getInterfaceName(),rpcRequest.getMethodName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.info("调用或发送时有错误发生：",e);
        }
        return result;
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) throws InvocationTargetException, IllegalAccessException {
        Method method;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
        } catch (NoSuchMethodException e) {
            return RpcResponse.fail(ResponseCode.METHOD_NOT_FOUND);
        }
        return method.invoke(service,rpcRequest.getParameters());
    }
}
