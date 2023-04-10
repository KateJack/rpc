package top.panl.rpc.exception;

import top.panl.rpc.enumeration.RpcError;

/**
 * ClassName: RpcException
 * Package: top.panl.rpc.exception
 * Description: RPC调用异常
 *
 * @Author liupan
 * @Create 2023/4/10 20:52
 * @Version 1.0
 */
public class RpcException extends RuntimeException{
    public RpcException(RpcError error,String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }
}
