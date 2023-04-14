package top.panl.rpc.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.entity.RpcResponse;
import top.panl.rpc.enumeration.SerializerCode;
import top.panl.rpc.exception.SerializeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ClassName: KryoSerializer
 * Package: top.panl.rpc.serializer
 * Description: Kryo序列化器
 *
 * @Author liupan
 * @Create 2023/4/14 11:08
 * @Version 1.0
 */
public class KryoSerializer implements CommonSerializer {

    private static final Logger logger = LoggerFactory.getLogger(KryoSerializer.class);

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(()->{
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)){
            Kryo kryo = kryoThreadLocal.get();
            kryo.writeObject(output,obj);
            kryoThreadLocal.remove();
            return output.toBytes();

        } catch (IOException e) {
            logger.error("序列化时有错误发生：",e);
            throw new SerializeException("序列化时有错误发生");
        }
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)){
            Kryo kryo = kryoThreadLocal.get();
            Object o = kryo.readObject(input,clazz);
            kryoThreadLocal.remove();
            return o;
        } catch (IOException e) {
            logger.error("反序列化时有错误发生：",e);
            throw new SerializeException("反序列化时有错误发生");
        }
    }

    @Override
    public int getCode() {
        return SerializerCode.valueOf("KRYO").getCode();
    }
}
