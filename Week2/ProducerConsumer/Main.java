package ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Basic Producer-Consumer (Synchronized) ===");
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Running the BlockingQueue producer-consumer
        System.out.println("\n=== BlockingQueue Producer-Consumer ===");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Thread blockingQueueProducer = new Thread(new BlockingQueueProducer(queue));
        Thread blockingQueueConsumer = new Thread(new BlockingQueueConsumer(queue));

        blockingQueueProducer.start();
        blockingQueueConsumer.start();

        try {
            blockingQueueProducer.join();
            blockingQueueConsumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
