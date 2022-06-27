package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak5;

public class MaxDivisorsMain {
    public static int divisorsCount(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        final long start = System.currentTimeMillis();
        int maxDivisors = 0;
        int value = 0;

        for (int i = 0; i < 100000; i++) {
            int currentDivisors = divisorsCount(i);
            if (currentDivisors > maxDivisors) {
                maxDivisors = currentDivisors;
                value = i;
            }
        }
        // algoritam kvadradni
        // za 10 000 brojeva je - 204ms
        // za 100 000 brojeva je - 18924ms

        final long end = System.currentTimeMillis() - start;
        System.out.println(String.format("Number %d has the maximal number of divisors - %d", value, maxDivisors));
        System.out.println("Search duration: " + end + "ms");

    }

}


