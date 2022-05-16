package multithread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {

        String message;

        BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        System.out.println("\n CLIENT STARTS");

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

        message = inFromClient.readLine();

        outToServer.writeBytes(message + "\n");

        clientSocket.close();

    }

}
