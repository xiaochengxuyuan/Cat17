import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Seanwu on 2016/10/17.
 */
public class Main {
    public static final String HTTP1 = "HTTP/1.1 ";
    public static final String HTTP_STATUS_CODE_200 = "200 OK\r\n";

    public static void main(String[] args) throws IOException {

        WebConfig context = Context.CONFIG.getContext();
        String listen = context.getValue(WebConfig.LISTEN);
        ServerSocket socket = new ServerSocket(Integer.valueOf(listen));
        int i = 0;
        while (true) {
            i++;
            Socket accept = socket.accept();
            InputStream is = accept.getInputStream();
            OutputStream os = accept.getOutputStream();
            String requestString = convertStreamToString(is);
            System.out.println("request is :\n" + requestString);

            if (requestString.equals("q")) {
                break;
            }

            File indexFile = new File(context.getValue(WebConfig.DOCUMENT_ROOT) + context.getValue(WebConfig.INDEX_FILE));
            String resultBody = "";
            if (!indexFile.exists()) {
                resultBody = "404!! indexFile not exists!!";
            }else{

                FileInputStream fs = new FileInputStream(indexFile);
                resultBody = convertStreamToString(fs);
            }

            is.close();
            os.close();
        }
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
        while (((is.available() > 0)) && (length = is.read(buffer)) != EOF) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
