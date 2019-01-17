package com.nxs.dataPackage.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 15:40
 * @Version: 1.0
 */
public class ServerMessageHandler extends ChannelInboundHandlerAdapter {

    private int messageCount = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String _msg = (String) msg;
        System.out.println("["+ (++messageCount) +"]接收到消息：" + _msg);

        // 注意：业务异常需要处理，不能不管，否则会调用exceptionCaught()
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
