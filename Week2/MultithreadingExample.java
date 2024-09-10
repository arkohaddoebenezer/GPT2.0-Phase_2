import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingExample {

    private static int counter = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        Runnable task1 = new Task("Thread 1");
        Runnable task2 = new Task("Thread 2");
        
        System.out.println("Submitting tasks to the thread pool");
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.shutdown();
        System.out.println("Executor service shutdown initiated");
    }
    static class Task implements Runnable {
        private String threadName;
        Task(String threadName) {
            this.threadName = threadName;
        }
        @Override
        public void run() {
            System.out.println(threadName + " started execution.");
            incrementCounter(threadName);
            System.out.println(threadName + " finished execution.");
        }
    }
    
    private static synchronized void incrementCounter(String threadName) {
        for (int i = 1; i <= 5; i++) {
            counter++;
            System.out.println(threadName + " incremented Counter to " + counter);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
                e.printStackTrace();
            }
        }
    }
}
