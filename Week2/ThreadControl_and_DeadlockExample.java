import java.util.concurrent.*;

public class ThreadControl_and_DeadlockExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // System.out.println("1. Demonstrating Thread Interruption:");
        // threadInterruptionExample();

        // System.out.println("\n2. Demonstrating Fork/Join Framework:");
        // forkJoinExample();

        System.out.println("\n3. Demonstrating Deadlock Scenario:");
        deadlockScenario();

        // System.out.println("\n4. Demonstrating Deadlock Prevention:");
        // deadlockPrevention();
    }

    public static void threadInterruptionExample() throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Working " + i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        });
    
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
    

    public static void forkJoinExample() throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            RecursiveTask<Integer> task = new RecursiveTask<>() {
                @Override
                protected Integer compute() {
                    if (getSurplusQueuedTaskCount() < 5) {
                        System.out.println("Forking task...");
                        RecursiveTask<Integer> subTask = new RecursiveTask<>() {
                            @Override
                            protected Integer compute() {
                                return 10;
                            }
                        };
                        subTask.fork();
                        return subTask.join();
                    } else {
                        return 5;
                    }
                }
            };
            System.out.println("Result: " + forkJoinPool.invoke(task));
        } finally {
            forkJoinPool.close();
        }
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
