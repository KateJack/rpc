package top.panl.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: HelloObject
 * Package: top.panl.rpc.api
 * Description: HelloObject对象实现Serializable接口，在调用过程中从客户端传递给服务端
 *
 * @Author liupan
 * @Create 2023/3/30 16:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}
