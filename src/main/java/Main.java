import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Seanwu on 2016/10/17.
 */
public class Main {
    public static final String HTTP1 = "HTTP/1.1 ";
    public static final String HTTP_STATUS_CODE_200 = "200 OK\r\n";

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8099);

        int i = 0;
        i++;
        Socket accept = socket.accept();
        InputStream is = accept.getInputStream();
        OutputStream os = accept.getOutputStream();
        String requestString = convertStreamToString(is);
        System.out.println(requestString);
        String s = HTTP1 + HTTP_STATUS_CODE_200 + new HttpHeader().setKey("Content-Type").setValue("text/html")
                + "Hello , the " + i + "st request\r\n";

        System.out.println("return :\n" + s);

        os.write(s.getBytes());
        is.close();
        os.close();

    }

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 把 InputStream 转化成String
     * 或许有更优雅的办法，但是先这样吧
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is/*, Charset encoding*/) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while (((is.available() > 0)) &&(length = is.read(buffer)) != EOF) {
            result.write(buffer, 0, length);
            System.out.println(result.toString());
        }
        return result.toString("UTF-8");
    }
}
