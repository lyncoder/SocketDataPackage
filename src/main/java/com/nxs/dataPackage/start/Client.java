package com.nxs.dataPackage.start;

import com.nxs.dataPackage.coder.CustomMessageEncoder;
import com.nxs.dataPackage.coder.RequestEncoder;
import com.nxs.dataPackage.handler.ClientMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 15:30
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_SNDBUF,10)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 增加自定义编码器
                        socketChannel.pipeline().addLast((ChannelHandler) new RequestEncoder());
                        socketChannel.pipeline().addLast(new ClientMessageHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
