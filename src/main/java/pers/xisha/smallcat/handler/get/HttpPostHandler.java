package pers.xisha.smallcat.handler.get;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import pers.xisha.smallcat.handler.HttpRequestHandler;

import java.nio.charset.Charset;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderValues.*;

/**
 * Created by SeanWu on 2017/7/22.
 */
public class HttpPostHandler implements HttpRequestHandler {

    @Override
    public String handle(FullHttpRequest request) {
        String uri = request.uri();
        CharSequence mimeType = HttpUtil.getMimeType(request);
        AsciiString mimeTypeString = new AsciiString(mimeType);

        if (mimeTypeString.equals(MULTIPART_FORM_DATA)){

        }else if (mimeTypeString.equals(APPLICATION_X_WWW_FORM_URLENCODED)){

        }else if (mimeTypeString.equals(APPLICATION_JSON)){
            String jsonStr = request.content().toString(Charset.defaultCharset());
            JSONObject obj = JSON.parseObject(jsonStr);
            for(Map.Entry<String, Object> item : obj.entrySet()){
                System.out.println(item.getKey()+"="+item.getValue().toString());
            }
        }else if(mimeTypeString.equals(APPLICATION_OCTET_STREAM)){

        }
        System.out.println(mimeType);
        QueryStringDecoder queryString = new QueryStringDecoder(uri);
        // 这里应该根据 uri 去选择相应的实现类 TODO
        return "success";
    }
}
