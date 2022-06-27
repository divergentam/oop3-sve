package rs.ac.ni.oop3.tamara333.vezba6.zadatak9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PrimeWorker extends Thread {

    private final String workerName;
    private final int[] array;
    private final int from;
    private final int to;


    int count = 0;
    private List<Integer> value1 = new ArrayList<>();
    private List<Integer> value2 = new ArrayList<>();


    private boolean primeCheck(int number) {
        boolean prime;
        prime = (number > 2 && number % 2 != 0);
        int candidate = 3;

        while (prime && candidate <= Math.sqrt(number)) {
            if (number % candidate == 0) {
                prime = false;
            } else {
                candidate += 2;
            }
        }

        return prime;
    }

    @Override
    public void run() {

        final long start = System.currentTimeMillis();

        System.out.println(from + " - "+ to);

        for (int i = from; i < to; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (primeCheck(array[i] + array[j])) {
                    value1.add(array[i]);
                    value2.add((array[j]));
                    count++;
                }
            }
        }

        for (int i = 0; i < value2.size(); i++) {
            System.out.printf("(%d + %d)%n", value1.get(i), value2.get(i));
        }

        final long end = System.currentTimeMillis() - start;
        System.out.printf("Worker %s finished in %dms%n", workerName, end);

    }
}
