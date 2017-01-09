import java.util.HashMap;
import java.util.Map;

/**
 * Created by SeanWu on 2017/1/9.
 */
public enum Context {

    /**
     * 一些配置
     */
    CONFIG();
    /**
     * 配置具体的值
     */
    private WebConfig webconfig = null;

    private Context() {
        Map<String,String> configMap = new HashMap<>();
        configMap.put(WebConfig.LISTEN, "8099");
        configMap.put(WebConfig.DOCUMENT_ROOT, "D:\\Code\\SmallCat\\src\\webapp\\");
        configMap.put(WebConfig.INDEX_FILE, "index.html");
        webconfig = new WebConfig(configMap);
        System.out.println("WebContext has been inited");
    }

    public WebConfig getContext() {
        return webconfig;
    }

}
