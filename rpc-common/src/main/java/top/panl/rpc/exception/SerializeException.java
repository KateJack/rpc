package top.panl.rpc.exception;

/**
 * ClassName: SerializeException
 * Package: top.panl.rpc.exception
 * Description: 序列化异常
 *
 * @Author liupan
 * @Create 2023/4/14 11:06
 * @Version 1.0
 */
public class SerializeException extends RuntimeException{
    public SerializeException(String msg) {
        super(msg);
    }
}
