package com.nxs.dataPackage.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 15:32
 * @Version: 1.0
 */
public class CustomMessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byte[] body = ((ByteBuf)o).array();

        // 数据长度
        int dataLength = body.length;
        // 缓冲区先写入数据长度
        byteBuf.writeInt(dataLength);
        // 再写入数据
        byteBuf.writeBytes(body);
    }
}
