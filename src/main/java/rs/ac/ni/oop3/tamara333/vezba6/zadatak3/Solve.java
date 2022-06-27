package rs.ac.ni.oop3.tamara333.vezba6.zadatak3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class Solve {
    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<Point> pointsA = new ArrayList<>();
        List<Point> pointsB = new ArrayList<>();
        final int randomValue = RANDOM.nextInt(1000);
        IntStream.range(0 , 15)
                .forEach(i -> pointsA.add(new Point(randomValue, randomValue)));

        IntStream.range(0 , 51)
                .forEach(i -> pointsB.add(new Point(randomValue, randomValue)));

        final int t = 7;

        solve(pointsA, pointsB, t);
    }

    public static void solve(List<Point> a, List<Point> b, int t){

    }
}
