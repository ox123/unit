package org.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TestServerInitalizer());
        ChannelFuture future = null;
        try {
            future = serverBootstrap.bind(8899).sync();
            future.addListener((ChannelFutureListener) channelFuture -> {
                Channel channel = channelFuture.channel();
//                channel.writeAndFlush()
//                    Unpooled.
            });
//            future.channel().writeAndFlush()
            future.channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        finally {
//            System.out.println("xxx");
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }

    }
}
