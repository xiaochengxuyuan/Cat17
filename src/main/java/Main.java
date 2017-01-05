import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Seanwu on 2016/10/17.
 */
public class Main {
    public static final String HTTP1 = "HTTP/1.1 ";
    public static final String HTTP_STATUS_CODE_200 = "200 HTTP_STATUS_CODE_200\r\n";

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8082);

        int i = 0;
        while (true) {
            i++;
            Socket accept = socket.accept();
            InputStream is = accept.getInputStream();
            OutputStream os = accept.getOutputStream();
            String requestString = convertStreamToString(is);
            if (requestString.equals("exit")) {
                break;
            }
            System.out.println(requestString);
            String s = HTTP1 + HTTP_STATUS_CODE_200 + new HttpHeader().setKey("Content-Type").setValue("text/html")
                    + "<html><head></head><body><h1>Hello , the " + i + "st request </h1></body></html>";
            os.write(s.getBytes());
            is.close();
            os.close();
        }

    }

    /**
     * 把 InputStream 转化成String
     * 或许有更优雅的办法，但是先这样吧
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
