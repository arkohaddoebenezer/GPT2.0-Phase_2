package ProducerConsumer;

public class SharedResource {
    private int item;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            System.out.println("Wait for consumer to consume");
            wait();
        }
        item = value;
        System.out.println("Produced: " + item);
        available = true;
        System.out.println("Notify consumer to consume");
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        System.out.println("Consumed: " + item);
        available = false;
        System.out.println("Notify produce to produce");
        notify();
    }
}
