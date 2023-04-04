package top.panl.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.api.HelloObject;
import top.panl.rpc.api.HelloService;

/**
 * ClassName: HelloServiceImpl
 * Package: top.panl.test
 * Description: 在服务端进行HelloService接口的实现
 *
 * @Author liupan
 * @Create 2023/3/30 17:05
 * @Version 1.0
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "这是调用的返回值，id = " + object.getId();
    }
}
