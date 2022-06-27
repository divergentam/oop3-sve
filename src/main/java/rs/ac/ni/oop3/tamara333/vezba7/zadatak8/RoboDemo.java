package rs.ac.ni.oop3.tamara333.vezba7.zadatak8;

import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoboDemo {
    public static void main(String[] args) {
        final int m = 5;
        final int n = 3;

        final Matrix matrix = new Matrix(m);
        final CountDownLatch countDownLatch = new CountDownLatch(n);
        final ExecutorService executorService = Executors.newCachedThreadPool();

        final List<Robot> robots = new ArrayList<>(n);
        robots.add(new Robot(1, m, matrix, List.of('U', 'L', 'D', 'R'), 1, countDownLatch, 2, 2));
        robots.add(new Robot(2, m, matrix, List.of('D', 'L', 'R', 'U'), 2, countDownLatch, 0, 4));
        robots.add(new Robot(3, m, matrix, List.of('R', 'U', 'L', 'D'), 3, countDownLatch, 3, 0));

        robots.forEach(executorService::submit);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix.matrix[i][j] + " ");
            }
            System.out.print("\n");
        }

    }
}
