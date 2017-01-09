/**
 * Created by SeanWu on 2017/1/5.
 */
public class HttpHeader {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public HttpHeader setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HttpHeader setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return  key + ": " + value + "\r\n\r\n";
    }
}
