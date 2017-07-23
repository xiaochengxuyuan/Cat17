package pers.xisha.smallcat.handler.get;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import pers.xisha.smallcat.handler.HttpRequestHandler;

/**
 * Created by SeanWu on 2017/7/22.
 */
public class HttpPutHandler implements HttpRequestHandler {

    @Override
    public String handle(FullHttpRequest request) {
        String uri = request.uri();
        QueryStringDecoder queryString = new QueryStringDecoder(uri);
        // 这里应该根据 uri 去选择相应的实现类 TODO
        return "success";
    }
}
