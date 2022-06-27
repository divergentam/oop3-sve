package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak5;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DivisorsWorker extends Thread {
    // napravimo npr 10 threadova i svakom od Threadova posaljem 10 000 broijeva
    // kad se racunica zavrsi uporedimo maximume

    private final String workerName;
    private final int from;
    private final int to;

    private int maxDivisors;
    private int value;

    private static int divisorsCounter(int num) {
        int count = 0;

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                count++;
            }
        }
        return count;
    }

    public void run() {
        maxDivisors = 0;
        value = 0;
        final long start = System.currentTimeMillis();

        for (int i = from; i < to; i++) {
            int currentDivisors = divisorsCounter(i);
            if (currentDivisors > maxDivisors) {
                maxDivisors = currentDivisors;
                value = i;
            }
        }

        final long end = System.currentTimeMillis() - start;
        System.out.printf("Worker %s finished in %dms %n", workerName, end);

    }

}
