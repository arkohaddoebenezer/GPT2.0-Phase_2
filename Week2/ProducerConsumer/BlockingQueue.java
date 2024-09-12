package ProducerConsumer;

import java.util.concurrent.BlockingQueue;

class BlockingQueueProducer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public BlockingQueueProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                queue.put(i);
                System.out.println("BlockingQueue Produced: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class BlockingQueueConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public BlockingQueueConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int item = queue.take();
                System.out.println("BlockingQueue Consumed: " + item);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
