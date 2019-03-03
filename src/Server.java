

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    BufferedReader in;
    PrintWriter out;


    public Server(int port) throws IOException {
        ServerSocket listener = new ServerSocket(port);
        Socket socket = listener.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String output) {
        out.println(output);
    }
}
