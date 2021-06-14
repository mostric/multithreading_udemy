package multithreading.udemy.example4.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Join {
    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 34444444435L, 2324L, 4656L, 23L, 2435L, 5566L);
        // calculate factorials of !0L, !3435L, !34435L, !2324L, !4656L, !23L, !2435L, !5566L
        List<FactorialThread> threads = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (FactorialThread thread : threads) {
            try {
                thread.join(4000);
            } catch (InterruptedException ignored) {
            }
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of the number " + inputNumbers.get(i) + " is " + factorialThread.getResult().toString().substring(0, Math.min(factorialThread.getResult().toString().length(), 10)) + "...");
            } else {
                factorialThread.interrupt();
            }
        }
    }

    public static class FactorialThread extends Thread {
        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        private BigInteger factorial(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = inputNumber; i > 0; i--) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread with input number " + getInputNumber() + " was prematurely interrupted.");
                    return BigInteger.ZERO;
                }
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public long getInputNumber() {
            return inputNumber;
        }
    }
}
