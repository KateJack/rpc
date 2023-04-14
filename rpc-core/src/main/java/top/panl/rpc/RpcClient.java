package top.panl.rpc;

import top.panl.rpc.entity.RpcRequest;

/**
 * ClassName: RpcClient
 * Package: top.panl.rpc
 * Description:  客户端类通用接口
 *
 * @Author liupan
 * @Create 2023/4/13 10:17
 * @Version 1.0
 */
public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
