package multithread;

import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket clientSocket = new Socket("localhost", 6789);

        Queue receiveQueue = new Queue();
        Queue sendQueue = new Queue();

        Producer p0 = new Producer(0, clientSocket, receiveQueue, sendQueue);
        Producer p1 = new Producer(1, clientSocket, receiveQueue, sendQueue);
        Consumer c0 = new Consumer(0, clientSocket, receiveQueue, sendQueue);
        Consumer c1 = new Consumer(1, clientSocket, receiveQueue, sendQueue);

        Thread t0 = new Thread(p0);
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c0);
        Thread t3 = new Thread(c1);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

    }

}
