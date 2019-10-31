import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Socket {

    public static void Main(String[] args) {

        String osoite = "www.hs.fi";
        int portti = 80;

        Socket socket = new Socket(osoite, portti);

        PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
        kirjoittaja.println("GET / HTTP/1.1");
        kirjoittaja.println("Host: " + osoite);
        kirjoittaja.println();
        kirjoittaja.flush();

        Scanner lukija = new Scanner(socket.getInputStream());
        while (lukija.hasNextLine()) {
            System.out.println(lukija.nextLine());
        }

    }
}