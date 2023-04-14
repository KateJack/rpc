package top.panl.rpc.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.enumeration.RpcError;
import top.panl.rpc.exception.RpcException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: DefaultServiceRegistry
 * Package: top.panl.rpc.registry
 * Description: 默认的服务注册表
 *
 * @Author liupan
 * @Create 2023/4/10 20:29
 * @Version 1.0
 */
public class DefaultServiceRegistry implements ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);

    private static final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private static final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public <T> void register(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if (registeredService.contains(serviceName)) return;
        registeredService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        for(Class<?> i: interfaces) {
            serviceMap.put(i.getCanonicalName(),service);
        }
        logger.info("向接口：{} 注册服务：{}",interfaces,serviceName);
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
