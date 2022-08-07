package com.jsamuel.study.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static int HEADER_LENGTH = 4;

    public void bind(int port) throws Exception {
        ServerBootstrap b = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        // 构造对应的pipeline
        b.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipelines = Channels.pipeline();
                pipelines.addLast(MessageHandler.class.getName(), new MessageHandler());
                return pipelines;
            }
        });

        // 监听端口号
        b.bind(new InetSocketAddress(port));
    }

    // 消息处理
    static class MessageHandler extends SimpleChannelHandler {

        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            // 接收客户端请求
            ChannelBuffer channelBuffer = (ChannelBuffer) e.getMessage();
            String message = new String(
                    channelBuffer.readBytes(channelBuffer.readableBytes()).array(),
                    "UTF-8");
            logger.info("netty server received content [{}]", message);

            // 发送回执给客户端
            byte[] body = "netty server received message.".getBytes();
            byte[] header = ByteBuffer.allocate(HEADER_LENGTH)
                    .order(ByteOrder.BIG_ENDIAN)
                    .putInt(body.length)
                    .array();
            Channels.write(ctx.getChannel(), ChannelBuffers.wrappedBuffer(header, body));
            logger.info("netty server send callback, time={}", System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        try {
            new NettyServer().bind(8001);
        } catch (Exception e) {
            logger.error("init netty server failed", e);
        }
    }

}
