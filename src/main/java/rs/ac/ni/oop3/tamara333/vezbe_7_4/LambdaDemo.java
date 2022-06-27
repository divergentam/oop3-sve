package rs.ac.ni.oop3.tamara333.vezbe_7_4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaDemo {

    public static void main(String[] args) {
      /*  Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        };*/
        //MOZE DA SE ZAMENI LAMBDA IOZRAZOM

     /*   Predicate<Integer> isEven = number -> number % 2 == 0;
        check(9, isEven);*/

        check(10, evenChecker());


        // IntStream.range(0, 10) - br od 1, 2, ... , 9
        // IntStream.rangeClosed() - br od 0, 1, 2, ... , 10

        System.out.println("There are " + IntStream.rangeClosed(0, 1000)
                .filter(LambdaDemo::isPrime)
                .count() + " prime numbers in closed range [0, 1000]");

        IntStream.rangeClosed(0, 1000)
                .filter(LambdaDemo::isPrime)
                .forEach(el -> System.out.print(el + " "));

        /*jos jedan nacin bez metoda*/

        System.out.println("\n\nFor big range the fastest way to found which numbers are primes is this: ");

        IntStream.rangeClosed(0, 1000)
                .filter(v -> BigInteger.valueOf(v).isProbablePrime(1))
                .forEach(el -> System.out.print(el + " "));

        System.out.println("\n\nIn closed range [0, 1000] all palindromes are:");
        IntStream.rangeClosed(0, 1000)
                .filter(LambdaDemo::isPalindrome)
                .forEach(el -> System.out.print(el + " "));

        System.out.println("\n\nII way ---------- in closed range [0, 100] all palindromes are:");
        IntStream.rangeClosed(0, 100)
                .filter(palindromeChecker())
                .forEach(el -> System.out.print(el + " "));

        System.out.println("\n\nIs 1212 palindrome? ");
        check(1212, LambdaDemo::isPalindrome2);

        check(78, LambdaDemo::isPalindrome3);

        System.out.println("Sum of digits in 12345 is: " + sumOfDigits(12345));
        System.out.println("Sum of odd digits in 12345 is: " + sumOfOddDigits(12345));
    }

    //  ZADATAK 3  - a
    private static int sumOfDigits(int value) {

        return IntStream.iterate(value, el -> el > 0, el -> el /10)
                .map(el -> el % 10)
                .sum();
    }

    //  ZADATAK 3 - b
    private static int sumOfOddDigits(int value){
        return IntStream.iterate(value, el -> el >0, el-> el /10)
                .map(el -> el % 10)
                .filter(el -> el % 2 == 1)
                .sum();
    }

  /*  private static int sumOfDigits(int value) {
        int sum = 0;
        while(value > 0){
            sum += value % 10;
            value /= 10;
        }
        return sum;
    }*/

    private static Predicate<Integer> evenChecker() {
        return v -> v % 2 == 0;
    }

   /* private static IntPredicate primeChecker() {
        return LambdaDemo::isPrime;
    }*/

    private static IntPredicate palindromeChecker() {
        return LambdaDemo::isPalindrome;
    }

    // Ovaj moze da narpravi problem u nekim situacijama
    private static boolean isPalindrome2(int v) {
        List<Integer> digits = new ArrayList<>();

        int x = v;
        while (x > 0) {
            digits.add(x % 10);
            x /= 10;
        }

        int invV = 0;
        for (int d : digits) {
            invV = 10 * invV + d;
        }
        return v == invV;
    }

    private static boolean isPalindrome3(int v) {
        int x = v;

        List<Integer> digits = IntStream.iterate(x, res -> res > 0, res -> res / 10)
                .map(res -> res % 10)
                .boxed().toList();

        for (int d : digits) {
            System.out.print(d + " ");
        }
        System.out.println("\n");


        return true;
    }

    //ovaj ne pravi problem
    private static boolean isPalindrome4(int v) {
        String s = Integer.toString(v);
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    private static boolean isPalindrome(int v) {
        List<Integer> digits = new ArrayList<>();
        int x = v;
        while (x > 0) {
            int c = x % 10;
            digits.add(c);
            x /= 10;
        }

        boolean palindrome = true;
        int len = digits.size() - 1;
        for (int i = 0; palindrome && i <= len; i++) {
            if (digits.get(i) != digits.get(len - i)) {
                palindrome = false;
            }
        }

        return palindrome;
    }

    private static boolean isPrime(int v) {
        boolean prime = v == 2 || (v > 2 && v % 2 != 0);
        int candidate = 3;

        while (prime && candidate <= Math.sqrt(v)) {
            if (v % candidate == 0) {
                prime = false;
            } else candidate += 2;
        }

        return prime;
    }

    private static void check(int number, Predicate<Integer> checker) {
        System.out.println(checker.test(number));
    }

}
