package multithread;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

    public void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(6789);

        System.out.println("\n MULTISERVER STARTS");

        while (true) {

            Socket connectionSocket = serverSocket.accept();

            ServerThread thread =
                    new ServerThread(connectionSocket);

            thread.start();
        }
    }
}
