package multithreading.udemy.example1.threadinheritance;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new NewThread();
        thread1.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // Code that run in a new thread
            System.out.println("Hello from " + this.getName());
        }
    }
}
