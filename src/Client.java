

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private int port;
    private String ip;
    
    private BufferedReader in;
    private PrintWriter out;
    
    private Socket socket;

    public Client(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public int connect() {
        String serverAddress = ip;
        socket = null;
        try {
            socket = new Socket(serverAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }

        System.out.println("CONNECTED TO SERVER");
        return 0;

    }

    public void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
        	if(in.ready()) {
        		return in.readLine();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String output) {
        out.println(output);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
  