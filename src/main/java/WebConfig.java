import java.util.Map;

/**
 * Created by SeanWu on 2017/1/9.
 */
public final class WebConfig {

    /**
     * 获取监听端口的 Key
     */
    public static final String LISTEN = "listen";
    /**
     * 获取 web 根目录的 Key
     */
    public static final String DOCUMENT_ROOT = "DocumentRoot";
    /**
     * Web App 的 index 文件
     */
    public static final String INDEX_FILE = "indexFile";

    /**
     * 网站的一些配置，先暂时这么存着吧
     */
    Map<String, String> webconfig;

    private WebConfig() {
    }

    public WebConfig(Map<String, String> webconfig) {
        this.webconfig = webconfig;
    }

    public String getValue(String key) {
        if (key == null) return null;
        return webconfig.get(key);
    }
}
