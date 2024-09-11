package ProducerConsumer;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public BlockingQueueConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int item = queue.take();
                System.out.println("BlockingQueue Consumed: " + item);
                Thread.sleep(100); // Simulating some delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
