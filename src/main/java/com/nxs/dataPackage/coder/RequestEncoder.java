package com.nxs.dataPackage.coder;

import com.nxs.dataPackage.constant.ConstantValue;
import com.nxs.dataPackage.model.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 16:55
 * @Version: 1.0
 */
public class RequestEncoder extends OneToOneEncoder {
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        Request request = (Request) msg;
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        //包头
        buffer.writeInt(ConstantValue.FLAG);
        buffer.writeShort(request.getModule());
        buffer.writeShort(request.getCmd());
        buffer.writeInt(request.getDataLength());
        if(request.getData() != null){
            buffer.writeBytes(request.getData());
        }
        return buffer;
    }
}
