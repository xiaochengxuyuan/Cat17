package pers.xisha.smallcat.handler;

import io.netty.handler.codec.http.HttpMethod;
import pers.xisha.smallcat.handler.get.HttpDeleteHandler;
import pers.xisha.smallcat.handler.get.HttpGetHandler;
import pers.xisha.smallcat.handler.get.HttpPostHandler;
import pers.xisha.smallcat.handler.get.HttpPutHandler;

/**
 * Created by SeanWu on 2017/7/23.
 */
public class HttpHandlerFactory {
    public static HttpRequestHandler createHandlerByMethod(HttpMethod httpMethod){

        if (httpMethod.equals(HttpMethod.GET)){
            return new HttpGetHandler();
        }else if (httpMethod.equals(HttpMethod.POST)){
            return new HttpPostHandler();
        }else if (httpMethod.equals(HttpMethod.PUT)){
            return new HttpPutHandler();
        }else if (httpMethod.equals(HttpMethod.DELETE)){
            return new HttpDeleteHandler();
        }

        return new HttpGetHandler();

    }
}
