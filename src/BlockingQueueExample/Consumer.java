package BlockingQueueExample;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author hk
 */
public class Consumer implements Runnable {

    BlockingQueue<Integer> queue;
    int noOfIterations;

    public Consumer(BlockingQueue<Integer> q, int n) {
        queue = q;
        noOfIterations = n;
    }

    public void run() {
        try {
            for (int i = 0; i < noOfIterations; i++) {
                Integer element = queue.take();
                System.out.println("\tConsume: " + element);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer terminates");
        }
    }

}
