package multithreading.udemy.example3.termination;

public class BlockingTaskExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new BlockingTask());
        thread.start();
        Thread.sleep(4000);
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }
}
