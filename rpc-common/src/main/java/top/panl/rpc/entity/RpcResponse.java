package top.panl.rpc.entity;

import lombok.Data;
import top.panl.rpc.enumeration.ResponseCode;

import java.io.Serializable;


/**
 * ClassName: RpcResponse
 * Package: top.panl.rpc.entity
 * Description: 提供者执行完成或出错后向消费者返回的结果对象
 *
 * @Author liupan
 * @Create 2023/3/30 17:21
 * @Version 1.0
 */
@Data
public class RpcResponse<T> implements Serializable {

    /**
     * 响应状态码
     */
    private Integer statusCode;

    /**
     * 响应状态补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}

