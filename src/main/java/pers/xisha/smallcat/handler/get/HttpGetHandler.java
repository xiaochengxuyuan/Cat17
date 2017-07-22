package pers.xisha.smallcat.handler.get;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import pers.xisha.smallcat.handler.HttpRequstHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by SeanWu on 2017/7/22.
 */
public class HttpGetHandler implements HttpRequstHandler {

    @Override
    public void handle(FullHttpRequest request) {
        String uri = request.uri();
        QueryStringDecoder queryString = new QueryStringDecoder(uri);
        // 这里应该根据 uri 去选择相应的实现类 TODO
    }
}
