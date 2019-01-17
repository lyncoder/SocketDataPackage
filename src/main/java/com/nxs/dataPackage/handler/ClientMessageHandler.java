package com.nxs.dataPackage.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 15:36
 * @Version: 1.0
 */
public class ClientMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "hello,world";
        byte[] data;
        ByteBuf buf;
        for (int i=0;i<100;i++) {
            data = (msg+i).getBytes();
            buf = Unpooled.copiedBuffer(data);
            ctx.writeAndFlush(buf);
//            Thread.sleep(1000);
        }
        System.out.println("100条 消息发送完毕");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
