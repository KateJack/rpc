package top.panl.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.entity.RpcResponse;
import top.panl.rpc.enumeration.PackageType;
import top.panl.rpc.enumeration.RpcError;
import top.panl.rpc.exception.RpcException;
import top.panl.rpc.serializer.CommonSerializer;

import java.util.List;


/**
 * ClassName: CommonDecoder
 * Package: top.panl.rpc.codec
 * Description: 通用的解码拦截器
 *
 * @Author liupan
 * @Create 2023/4/13 21:23
 * @Version 1.0
 */
public class CommonDecoder extends ReplayingDecoder {
    private static final Logger logger = LoggerFactory.getLogger(CommonDecoder.class);
    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magic = in.readInt();
        if(magic != MAGIC_NUMBER) {
            logger.error("不识别的协议包：{}",magic);
            throw new RpcException(RpcError.UNKNOWN_PROTOCOL);
        }
        int packageCode = in.readInt();
        Class<?> packageClass;
        if(packageCode == PackageType.REQUEST_PACK.getCode()) {
            packageClass = RpcRequest.class;
        } else if(packageCode == PackageType.RESPONSE_PACK.getCode()) {
            packageClass = RpcResponse.class;
        } else {
            logger.error("不识别的数据包：{}",packageCode);
            throw new RpcException(RpcError.UNKNOWN_PACKAGE_TYPE);
        }
        int serializerCode = in.readInt();
        CommonSerializer serializer = CommonSerializer.getByCode(serializerCode);
        if(serializer == null) {
            logger.error("不识别的反序列化器：{}",serializerCode);
            throw new RpcException(RpcError.UNKNOWN_SERIALIZER);
        }
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Object obj = serializer.deserialize(bytes,packageClass);
        out.add(obj);
    }
}
