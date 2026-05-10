import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue <String> blockingQueue = null;

    public Consumer (BlockingQueue<String> queue) {
        this.blockingQueue = queue; 
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Consumer prende");
                this.blockingQueue.take();
            } catch (InterruptedException e) {
                System.out.println("Consumer was interrupted"); 
            }
            sleep(2000); 
            System.out.println("Consumer consumato"); 

        }
    }
    
    private static void sleep(long timeMillis) {
        try { 
            Thread.sleep(timeMillis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    } 
}