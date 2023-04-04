package top.panl.rpc.api;

/**
 * ClassName: HelloService
 * Package: top.panl.rpc.api
 * Description: 通用接口，
 *              hello方法需要传递一个HelloObject对象
 * @Author liupan
 * @Create 2023/3/30 17:01
 * @Version 1.0
 */
public interface HelloService {
    String hello(HelloObject object);
}
