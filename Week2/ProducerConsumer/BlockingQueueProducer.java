package ProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public BlockingQueueProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                queue.put(i);
                System.out.println("BlockingQueue Produced: " + i);
                Thread.sleep(15000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
