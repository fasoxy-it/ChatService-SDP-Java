package multithread;

import java.util.ArrayList;

public class Queue {

    public ArrayList<String> buffer = new ArrayList<String>();

    public synchronized void put(String message) {
        buffer.add(message);
        notify();
    }

    public synchronized String take() {
        String message = null;

        while (buffer.size() == 0) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        if (buffer.size() > 0 ) {
            message = buffer.get(0);
            buffer.remove(0);
        }

        return message;

    }
}
