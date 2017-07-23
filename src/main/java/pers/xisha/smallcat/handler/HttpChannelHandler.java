package pers.xisha.smallcat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.*;

import static io.netty.buffer.Unpooled.copiedBuffer;

/**
 * Created by SeanWu on 2017/7/21.
 */
public class HttpChannelHandler<C extends Channel> extends ChannelInitializer<C> {

    @Override
    protected void initChannel(C ch) throws Exception {
        ch.pipeline().addLast("codec", new HttpServerCodec());
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
        ch.pipeline().addLast("request", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                System.out.println("Connected!");
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                if (msg instanceof FullHttpRequest) {
                    FullHttpRequest request = (FullHttpRequest) msg;
                    String uri = request.uri();

                    //去除浏览器"/favicon.ico"的干扰
                    if(uri.equals("/favicon.ico")){
                        return;
                    }
                    HttpMethod method = request.method();

                    StringBuilder info = new StringBuilder("http uri:").append(uri).append(" method: ").append(method.name());
                    System.out.println(info);

                    // 这里应该想办法根据 uri 和 method 去找实现类 TODO
                    HttpRequestHandler handlerByMethod = HttpHandlerFactory.createHandlerByMethod(method);
                    String responseMessage = handlerByMethod.handle(request);

                    DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                            HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK,
                            copiedBuffer(responseMessage.getBytes())
                    );

                    if (HttpUtil.isKeepAlive(request)) {
                        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
                        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseMessage.length());
                        ctx.writeAndFlush(response);
                    }
                } else {
                    // TODO ?????
                    super.channelRead(ctx, msg);
                }
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
            }


            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                ctx.writeAndFlush(new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.INTERNAL_SERVER_ERROR,
                        copiedBuffer(cause.getMessage().getBytes())
                ));
            }
        });

    }

}
