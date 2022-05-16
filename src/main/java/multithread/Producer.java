package multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Producer implements Runnable {

    private int role;
    private Socket socket;
    private BufferedReader inFromOther;
    private Queue receiveQueue;
    private Queue sendQueue;

    public Producer(int role, Socket socket, Queue receiveQueue, Queue sendQueue) throws Exception {
        this.role = role;
        this.socket = socket;
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
        this.inFromOther = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {

        while (true) {
            if (role == 0) {

                try {
                    System.out.println("Waiting message...");
                    String result = inFromOther.readLine();
                    System.out.println("Message recives, (message puts inside recive queue)");
                    receiveQueue.put(result);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {

                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                sendQueue.put(message);
                System.out.println("messages puts inside send queue");

            }
        }

    }
}
