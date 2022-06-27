package rs.ac.ni.oop3.tamara333.vezba6.zadatak9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrimeParallel {

    public static final int T = 3;

    public static void main(String[] args) throws InterruptedException {

        final long start = System.currentTimeMillis();
        final List<PrimeWorker> workers = new ArrayList<>();

        final int a[] = {2, 3, 4, 5, 6, 7, 8, 9, 12};

        final int GROUP_SIZE = a.length/T;


        IntStream.range(0, T - 1)
                .forEach(i -> workers.add(new PrimeWorker("Worker-"+ i, a, i * GROUP_SIZE , (i + 1) * GROUP_SIZE)));

        IntStream.range(T -1 , T)
                .forEach(i -> workers.add(new PrimeWorker("Worker-"+ i, a, i * GROUP_SIZE , a.length - 1)));

        workers.forEach(Thread::start);
        for(PrimeWorker worker : workers){
            worker.join();
        }

        int sum = 0;

        for(PrimeWorker worker : workers){
            sum += worker.getCount();
        }

        final long end = System.currentTimeMillis() - start;
        System.out.println("Number of prime sums is: " + sum);
        System.out.println("Search duration: " + end + "ms");
    }

}
