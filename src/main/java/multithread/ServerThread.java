package multithread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    public ServerThread(Socket socket) {

        connectionSocket = socket;

        try {

            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream())
                    );

            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

        } catch (IOException exception) {

            exception.printStackTrace();
        }

    }

    public void run() {

        String clientMessage;

        try {

            clientMessage = inFromClient.readLine();

            outToClient.writeBytes("SERVER RECIVES: " + clientMessage);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
