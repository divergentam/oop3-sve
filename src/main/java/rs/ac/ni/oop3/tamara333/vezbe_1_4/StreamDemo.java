package rs.ac.ni.oop3.tamara333.vezbe_1_4;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamDemo {

    private static IntStream createPipeLine(Stream<Integer> stream) {
        return stream
                .peek(e -> System.out.println("Currently processing element " + e))
                .filter(el -> el % 2 == 0)
                .peek(e -> System.out.println("Element " + e + " succesfuly"))
                .map(el -> el / 2)
                .mapToInt(el -> el + 3);
    }

 /*   private static List<Integer> filter(List<Integer> list) {
        final List<Integer> result = new ArrayList<>();
       *//* for (final Integer element : list) {
            if (element % 2 == 0) {
                result.add(element);
            }
        }
        return result;*//*
        return list.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toList());

    }

    private static List<Integer> map(List<Integer> list) {
        final List<Integer> result = new ArrayList<>();
        return list.stream()
                .map(el -> el / 2)
                .collect(Collectors.toList());
        *//*
        for (final Integer element : list) {
            result.add(element / 2);
        }

        return result;*//*
    }
    OVO NIJE DOBRO jer trosi memoriju za cuvanbje listi i nije dobar nacin
*/

    private static Stream<Integer> filter(Stream<Integer> list) {
        return list.filter(e -> e % 2 == 0);
    }

    private static Stream<Integer> process(Stream<Integer> list) {
        return list.map(el -> el / 2);
    }

    public static void main(String[] args) {
//        final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // inmutable liste tj ne mozemo da kazemo numbers.add(11);
        // to znaci da su liste fiksne duzine i ne mogu da se menjaju

        final List<Integer> finalNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // mozemo da za vec uradjenu listu menjamo vrednosti ali bez menjanja duzine: numbers.set(0, 10);
        //ALI TO NE MOZEMO DA RADIMO ZA List.of (jer je ova lista konstantna) vec samo za Arrays.asList

        final List<Integer> numbers = new ArrayList<>(finalNumbers);
        // ovko mozemo da kazmeo numbers.add(12);

        int sum = 0;

        //1. nacin prolaska
        /*for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i) % 2 == 0){
                sum += numbers.get(i);
            }
        }*/

        //2. nacin prolaska
        for (int number : numbers) {
            if (number % 2 == 0) {
                sum += number;
            }
        }

        //3. nacin prolaska
        numbers.removeIf(number -> number % 2 != 0);
        /*final Iterator<Integer> it = numbers.iterator();
        while(it.hasNext()){
            final int number = it.next();
            if(number % 2 != 0){
                it.remove();
            }
        }*/

        for (int number : numbers) {
            System.out.println(number);
        }

        System.out.println("Suma svih elementa iz niza : " + sum);

        //4. nacin ako zelimo samo da ih prikazemo ili tako nesto jednostavno
        numbers.forEach(e -> System.out.println(e + 1));

        //5. nacin su STREAMOVI - zelimo da obradimo el ali da samo jednom prodjemo kroz kolekciju
        System.out.println("Stream: ");

        numbers.stream()
                //.stream ne znaci da cemo uvek da prolazimo kroz sve el. numbers-a vec to odredjuju metode kao
                // sto su TERMINALNE METODE: .sum(); .max(); .forEach()...
                .peek(e -> System.out.println("Currently processing element " + e))
                .filter(el -> el % 2 == 0)
                .map(el -> el / 2)
                .mapToInt(el -> el + 3)
                .forEach(System.out::println);

/*
        System.out.println(pipe.max());
        npr ako sada zelimo da napisemo da je ovaj srteam IntStream i nakon sto smo sve ove
        operacije izvrsili nad streamom zelimo da opet kazemo intStream.sum() to nije moguce
        jer smo vec jednom prosli kroz stream

        to niej prakticno jer bi morali da dupliramo kod
        ili da imamo metod koji radi sa strimovima tj istim streamom
        pa onda mogu da dva ili vise puta pozovem i prodjem kroz numbers
*/
        System.out.println("Sum: " + createPipeLine(numbers.stream()).sum());
        System.out.println("Maximal element: " + createPipeLine(numbers.stream()).max().getAsInt());

        int suma = process(filter(numbers.stream())).mapToInt(value -> value).sum();
        System.out.println("Sum is now : " + suma);

        /*  postoji i metod .sum()   */

        final List<Integer> oddNumbers = List.of(1, 3, 5, 7, 9);
        final List<Integer> duplicates = List.of(1, 1, 2, 8, 5, 6, 3, 5, 9, 7, 9);

        int x = oddNumbers.stream()
                .filter(e -> e % 2 == 0)
                .findFirst()
                //.orElse(-1);
                .orElseGet(() -> {
                    log.error("x1 - You don't have even numbers in this array");
                    return -1;
                });
        System.out.println(x);
        int x2 = numbers.stream()
                .filter(e -> e % 2 == 0)
                .findAny()
                .orElseGet(() -> {
                    log.error("x2 - You don't have even numbers in this array");
                    return -1;
                });
        System.out.println(x2);
        // Do sada nismo razmisljali sta nam vraca koj metod vec smo ih posmatrali kao posebne delove

        long x3 = duplicates.stream()
                .distinct() //ovaj metod je specifican jer vraca samo razlicite elemente -- NIJE TERMINALNI
                .count(); // -- TERMINALNI
        System.out.println(x3);

        //ako zelim da nadjem sumu
        int sumaa = numbers.stream()
                .reduce(0, (accumulated, currently) -> accumulated + currently); // njegov arg je akumulator
        // 0 ovde nije pocetna vrednost!!!!!!!!!! JER KADA SE PUSTI KROZ PARALELNI PROCES
        // NE VRACA DOBRE VREDNOSTI. Kada nije paralelno onda JESTE POCETNA
        System.out.println("Sum3: " + sumaa);

        int mult = numbers.stream()
                .limit(2)
                .reduce(1, (accumulated, current) -> accumulated * current);
        System.out.println("Multiplication with limit2 - 2*4 : " + mult);

        int mult2 = numbers.stream()
                .skip(4)
                .reduce(1, (accumulated, current) -> accumulated * current);
        System.out.println("Multiplication with skip4 - 10 : " + mult2);

        duplicates.stream().sorted(Comparator.naturalOrder() ).forEach(el -> System.out.print(el + " "));

        // u svakom do sad smo posmatrali samo trenutni el ali ovaj nam
        // sopusta da posmatramo trenutni el i rezultat operacija prethodnih el




    }
}
