package multithreading.udemy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            // Code that run  in a new thread
            System.out.println("We are in a new thread that goes by name: " + Thread.currentThread().getName() + ".");
            System.out.println("New thread priority is : " + Thread.currentThread().getPriority() + ".");
        },
                "new-worker-thread.");
        thread1.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Before starting a new thread. We are in thread: " + Thread.currentThread().getName());
        thread1.start();
        System.out.println("After starting a new thread. We are in thread: " + Thread.currentThread().getName());


        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("Intentional exception.");
        }, "misbehaving-thread.");

        thread2.setUncaughtExceptionHandler((thread, e) ->
                System.out.println("A critical error occurred in thread '" + thread.getName()
        + "' with the error: " + e.getMessage()));
        thread2.start();

        Thread.sleep(1000);
    }
}
