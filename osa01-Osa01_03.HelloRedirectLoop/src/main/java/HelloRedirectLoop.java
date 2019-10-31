
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            // waiting for a request
            Socket req = server.accept();

            // read request
            Scanner reader = new Scanner(req.getInputStream());

            String message = reader.nextLine();
            if (message.contains("/quit")) {
                break;
            }

            // write response
            PrintWriter writer = new PrintWriter(req.getOutputStream());
            writer.println("HTTP/1.1 302 Found");
            writer.println("Location: http://localhost:8080");
            writer.println();
            writer.flush();

            // closing resources
            writer.close();
            reader.close();
            req.close();
        }

    }
}
