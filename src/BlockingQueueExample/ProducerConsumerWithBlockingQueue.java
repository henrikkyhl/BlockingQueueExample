package BlockingQueueExample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerWithBlockingQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(4);
        
        // There are other implementations of the BlockingQueue interface.
        // For example:
        //BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);        
        
        Runnable p = new Producer(queue);
        Runnable c = new Consumer(queue, 10);
        
        // Run Producer and Consumer. They are run in separate thread pools
        // so that they can be stopped independently.
        ExecutorService producerExecutor = Executors.newFixedThreadPool(1);
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(1);        
        producerExecutor.submit(p);
        consumerExecutor.submit(c);
        
        // Shutdown Consumer and Producer.
        try{
            consumerExecutor.shutdown();
            consumerExecutor.awaitTermination(20, TimeUnit.SECONDS);            
        }
        catch (InterruptedException ex){
            Logger.getLogger("ThreadDemo").log(Level.SEVERE, null, ex);
        }
        finally {
            if (!consumerExecutor.isTerminated()){
                consumerExecutor.shutdownNow();
            }
            producerExecutor.shutdownNow();
        }
        
    }
    
}
