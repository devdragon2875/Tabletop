

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SubServer extends Thread {

    protected Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public SubServer(Socket clientSocket) throws IOException {

        this.socket = clientSocket;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(IOException e){
            System.err.println(e);
        }
    }

    public void run() {

        while(true){
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
