package top.panl.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: SerializerCode
 * Package: top.panl.rpc.enumeration
 * Description: 字节流中标识序列化和反序列化器
 *
 * @Author liupan
 * @Create 2023/4/13 20:59
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum SerializerCode {
    KRYO(0),
    JSON(1);
    private final int code;
}
