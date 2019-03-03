

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CentralServer {
	
    public CentralServer(int port) throws IOException {

        Socket socket = null;

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            System.out.println("STARTED SOCKET PART 0");
            socket = serverSocket.accept();

            System.out.println("STARTED SOCKET PART 1");

            new SubServer(socket).start();
            System.out.println("STARTED SOCKET PART 2");
        }
    }
    public static void main(String[] args) throws IOException {
		CentralServer server = new CentralServer(4444);
	}

}
