import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                map.put("Key" + i, i);
                System.out.println("Thread 1 added: Key" + i + " -> " + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                map.put("Key" + i, i);
                System.out.println("Thread 2 added: Key" + i + " -> " + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final map content: " + map);
    }
}
