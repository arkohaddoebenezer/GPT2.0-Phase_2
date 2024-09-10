import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> performTask("Task 1"));
        executor.submit(() -> performTask("Task 2"));

        executor.shutdown();
    }

    private static void performTask(String taskName) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Count: " + i);
        }
    }
}
