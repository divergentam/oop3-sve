package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class MaxDivisorsParallel {

    private static final int GROUP_SIZE = 10000;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        final List<DivisorsWorker> workers = new ArrayList<>();

        /*for(int i =0; i< 10; i++){
            workers.add(new DivisorsWorker(GROUP_SIZE * i + 1, GROUP_SIZE * (i + 1)));
        }*/

        IntStream.range(0, 10)
                .forEach(i -> workers.add(new DivisorsWorker("Worker-" + i, GROUP_SIZE * i + 1, GROUP_SIZE * (i + 1))));

       /* for(DivisorsWorker worker : workers){
            worker.start();
        }*/

        workers.forEach(Thread::start);
        for (DivisorsWorker worker : workers) {
            worker.join();
        }

        final DivisorsWorker max = workers.stream()
                /*return o1.getMaxDivisors() > o2.getMaxDivisors() ? o1.getMaxDivisors() : o2.getMaxDivisors();*/
                .max(Comparator.comparingInt(DivisorsWorker::getMaxDivisors)).get();

        long end = System.currentTimeMillis() - start;

        System.out.println(String.format("Number %d has the maximal number of divisors - %d",
                max.getValue(),
                max.getMaxDivisors()));
        System.out.println("Search duration: " + end + "ms");

        /*int maxDiv = 0;
        int val = 0;

        for (DivisorsWorker worker : workers) {
            if (worker.getMaxDivisors() > maxDiv) {
                maxDiv = worker.getMaxDivisors();
                val = worker.getValue();
            }

        }*/


        //U STREMOVIMA NE HVATAMO IZUZETKE LEPO

    }

}
