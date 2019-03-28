package BlockingQueueExample;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> q) {
        queue = q;
    }

    public void run() {
        int i = 0;

        try {
            while (true) {
                queue.put(++i);
                System.out.println("Produce:" + i);                
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer terminates");
        }
    }
}
