package multithread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Consumer implements Runnable {

    private int role;
    private Socket socket;
    private DataOutputStream outToOther;
    private Queue reciveQueue;
    private Queue sendQueue;

    public Consumer(int role, Socket socket, Queue reciveQueue, Queue sendQueue) throws Exception {
        this.role = role;
        this.socket = socket;
        this.reciveQueue = reciveQueue;
        this.sendQueue = sendQueue;
        this.outToOther = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {

        while (true) {
            if (role == 0) {
                String message = reciveQueue.take();
                System.out.println("Message recives: " + message);
            } else {
                String message = sendQueue.take();
                System.out.println("Read the message from the send queue and now send it");
                try {
                    outToOther.writeBytes(message);
                    System.out.println("Sent!");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
