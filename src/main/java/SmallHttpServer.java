import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pers.xisha.smallcat.handler.HttpChannelHandler;

public class SmallHttpServer {

    private ChannelFuture channel;
    private final EventLoopGroup masterGroup;
    private final EventLoopGroup slaveGroup;

    public SmallHttpServer() {
        masterGroup = new NioEventLoopGroup();
        slaveGroup = new NioEventLoopGroup();
    }

    public void start() throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        WebConfig context = Context.CONFIG.getContext();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(masterGroup, slaveGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpChannelHandler<SocketChannel>())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        String listen = context.getValue(WebConfig.LISTEN);
        System.out.println(listen);
        channel = serverBootstrap.bind(Integer.valueOf(listen)).sync();
    }


    private void shutdown() {
        slaveGroup.shutdownGracefully();
        masterGroup.shutdownGracefully();

        try {
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            // do nothing
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new SmallHttpServer().start();
    }


}
