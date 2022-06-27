package rs.ac.ni.oop3.tamara333.predavanja_25_3;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalDemo {

    /* private static int doOperation(int x, Operation operation){
        return operation.doOperation(x);
    }*/

    /* Ovde je moglo da se napravi klasa koja bi imala metod
    koji duplira moj broj ali da ta clasa implementira Function*/
    private static int doOperation(int x, Function<Integer, Integer> operation) {
        return operation.apply(x);
    }

    /*LAMBDA IZRAZI*/
    public static void main(String[] args) {
        final int value1 = doOperation(14, x -> 2 * x);
        final int value2 = doOperation(14, x -> 3 * x);

        printOperation(10, 5, Integer::sum);

        printOperation(10, 5, FunctionalDemo::complexComputation);
    }

    private static int complexComputation(int x, int y) {
        int pom = x - y;
        pom = x * pom * y;
        pom *= 3;
        return pom;
    }

    private static void printOperation(int a, int b, FunctionalOperation op) {
        System.out.println(op.compute(a, b));
    }

    public static void mainFunctionalForDoubleAndTriple(String[] args) {
        final int value1 = (doOperation(5, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 2 * integer;
            }
        }));

        final int value2 = (doOperation(5, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 3 * integer;
            }
        }));

        System.out.println(value1);
        System.out.println(value2);

        /*
         * Sada su mi one class-e i onaj interfejs visak
         * */

    }

    public static void main4StandardnaInterfejsa(String[] args) {
      /* 1. Supplier<Integer> s = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 10;
            }
        };  */

        /* 2. Predicate<Integer> p = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return false;
            }
        }   */

       /*  3.  Function<Integer, Double> f = new Function<Integer, Double>() {
            @Override
            public Double apply(Integer integer) {
                return integer.doubleValue();
            }
        };  */

       /*  4.  Consumer<Double> c = new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                aDouble *= 0.8; // snizenje cene za 20%
                System.out.print(aDouble);
            }
        };
        c.accept(14586.99);*/

    }
}
