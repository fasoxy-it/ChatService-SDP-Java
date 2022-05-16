package multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(6789);

        Socket connectionSocket = serverSocket.accept();

        Queue receiveQueue = new Queue();
        Queue sendQueue = new Queue();

        Producer p0 = new Producer(0, connectionSocket, receiveQueue, sendQueue);
        Producer p1 = new Producer(1, connectionSocket, receiveQueue, sendQueue);
        Consumer c0 = new Consumer(0, connectionSocket, receiveQueue, sendQueue);
        Consumer c1 = new Consumer(1, connectionSocket, receiveQueue, sendQueue);

        Thread t0 = new Thread(p0);
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c0);
        Thread t3 = new Thread(c1);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

        System.out.println("Server side all on!");

        try {
            t0.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
