
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            Socket socket = server.accept();

            Scanner reader = new Scanner(socket.getInputStream());
            String message = reader.nextLine();
            if (message.contains("/quit")) {
                break;
            }

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("HTTP/1.1 200 OK");
            pw.println("Location: http://localhost:8080/");
            pw.println();
            try {
                Files.lines(Paths.get("osa01-Osa01_02.HelloServer/index.html")).forEach(line -> pw.println(line));
            } catch (IOException e) {
                System.out.println(e);
            }
            pw.flush();

            pw.close();
            reader.close();
            socket.close();

        }

        server.close();
    }
}
