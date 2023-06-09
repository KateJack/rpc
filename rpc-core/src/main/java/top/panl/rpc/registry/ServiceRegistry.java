package top.panl.rpc.registry;

/**
 * ClassName: ServiceRegistry
 * Package: top.panl.rpc.registry
 * Description: 服务注册表通用接口
 *
 * @Author liupan
 * @Create 2023/4/10 20:25
 * @Version 1.0
 */
public interface ServiceRegistry {
    /**
     * 将一个服务注册进注册表
     * @param service  将注册的服务实体
     * @param <T> 服务实体类
     */
    <T> void register(T service);

    /**
     * 根据服务名称获取服务实体
     * @param serviceName  服务名称
     * @return  服务实体
     */
    Object getService(String serviceName);
}
