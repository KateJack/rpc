package top.panl.rpc.codec;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.panl.rpc.entity.RpcRequest;
import top.panl.rpc.enumeration.PackageType;
import top.panl.rpc.serializer.CommonSerializer;

/**
 * ClassName: CommonEncoder
 * Package: top.panl.rpc.codec
 * Description: 通用的编码拦截器
 *
 * @Author liupan
 * @Create 2023/4/13 11:06
 * @Version 1.0
 */
public class CommonEncoder extends MessageToByteEncoder {

    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    private final CommonSerializer serializer;

    public CommonEncoder(CommonSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeInt(MAGIC_NUMBER);
        if(msg instanceof RpcRequest) {
            out.writeInt(PackageType.REQUEST_PACK.getCode());
        } else {
            out.writeInt(PackageType.RESPONSE_PACK.getCode());
        }
        out.writeInt(serializer.getCode());
        byte[] bytes = serializer.serialize(msg);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
