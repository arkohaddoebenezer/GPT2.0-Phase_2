
public class SynchronizationExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 incrementing counter by 100");
            for (int i = 0; i < 50; i++) {
                counter.increment();
                System.out.println("Thread 1: "+i);
                try {
                    Thread.sleep(100);  // Sleep for 1 millisecond after increment
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 incrementing counter by 100");
            for (int i = 0; i < 50; i++) {
                counter.increment();
                System.out.println("Thread 2: "+i);
                try {
                    Thread.sleep(100);  // Sleep for 1 millisecond after increment
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final count: " + counter.getCount());
        // System.out.println("Deadlock scenario");
        // deadlockScenario();
        // System.out.println("Deadlock prevention");
        // deadlockPrevention();
    }
    
    public static void deadlockScenario() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 holding lock 1...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lock2) {
                    System.out.println("Thread 1 holding lock 2...");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 holding lock 2...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lock1) {
                    System.out.println("Thread 2 holding lock 1...");
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void deadlockPrevention() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 holding lock 1...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lock2) {
                    System.out.println("Thread 1 holding lock 2...");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 2 holding lock 1...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lock2) {
                    System.out.println("Thread 2 holding lock 2...");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}