package top.panl.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ResponseCode
 * Package: top.panl.rpc.enumeration
 * Description: 方法调用的响应状态码
 *
 * @Author liupan
 * @Create 2023/3/30 17:23
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200, "调佣方法成功"),
    FAIL(500, "调用方法失败"),
    METHOD_NOT_FOUND(500, "未找到指定方法"),
    CLASS_NOT_FOUND(500, "未找到指定类");

    private final int code;
    private final String message;
}
