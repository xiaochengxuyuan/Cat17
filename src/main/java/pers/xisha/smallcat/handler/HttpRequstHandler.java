package pers.xisha.smallcat.handler;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Created by SeanWu on 2017/7/22.
 */
public interface HttpRequstHandler {
    default void handle(FullHttpRequest request){
        String uri = request.uri();
        QueryStringDecoder queryString = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = queryString.parameters();
        System.out.println(JSON.toJSONString(parameters));
    }
}
