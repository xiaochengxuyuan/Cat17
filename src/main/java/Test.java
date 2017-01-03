import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Seanwu on 2016/10/17.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8082);

        int i = 0;
        while (true) {
            i ++;
            Socket accept = socket.accept();
            InputStream is = accept.getInputStream();
            OutputStream os = accept.getOutputStream();
            String requestString = convertStreamToString(is);
            if (requestString.equals("exit")) {
                break;
            }
            System.out.println(requestString);
            String s = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n\r\n" +
                    "<html><head></head><body><h1>Hello , the "+ i +"st request </h1></body></html>";
            os.write(s.getBytes());
            is.close();
            os.close();
        }

    }

    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
