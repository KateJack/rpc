package top.panl.rpc.serializer;



/**
 * ClassName: CommonSerializer
 * Package: top.panl.rpc.serializer
 * Description: 通用的序列化反序列化接口
 *
 * @Author liupan
 * @Create 2023/4/13 11:09
 * @Version 1.0
 */
public interface CommonSerializer {
    byte[] serialize(Object obj);
    Object deserialize(byte[] bytes, Class<?> clazz);

    int getCode();

    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
