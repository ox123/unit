package org.netty.demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                0,
                4,
                0 ,
                4));
        pipeline.addLast(new LengthFieldPrepender(4));
        // 解码器
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 编码器
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 定义自己编写的代码
        pipeline.addLast(new MyServerHandler());
    }
}
