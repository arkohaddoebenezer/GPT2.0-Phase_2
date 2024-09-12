import java.util.concurrent.*;

public class ThreadControl_and_DeadlockExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("1. Demonstrating Thread Interruption:");
        threadInterruptionExample();

        System.out.println("\n2. Demonstrating Fork/Join Framework:");
        forkJoinExample();
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
}
