package org.netty.chart;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChartServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup defaultGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("channelRead0");
        defaultGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + " 发送消息:" + msg);
            } else {
                ch.writeAndFlush("self send the msg");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        Channel channel = ctx.channel();
        //广播
        defaultGroup.writeAndFlush("new client join:" + channel.remoteAddress());
        defaultGroup.add(channel);


    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        Channel channel = ctx.channel();
        defaultGroup.writeAndFlush("new client " + channel.remoteAddress() + " leave now");
        defaultGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        Channel channel = ctx.channel();
        defaultGroup.writeAndFlush("new client " + channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        Channel channel = ctx.channel();
        defaultGroup.writeAndFlush("new client " + channel.remoteAddress() + " 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
