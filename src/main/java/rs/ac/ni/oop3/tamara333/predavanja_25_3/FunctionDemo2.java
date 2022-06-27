package rs.ac.ni.oop3.tamara333.predavanja_25_3;

import java.util.ArrayList;
import java.util.List;

public class FunctionDemo2 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = 0;

        for (final Integer el : numbers) {
            if (el % 2 == 0) {
                sum += 3 * el;
            }
        }
        System.out.println(sum);

        // ovo je samo za stampu el tj interiranje kroz listu
        numbers.forEach(System.out::println);

        //ako hocemo da i s tim elementima radimo nesto:
        numbers.stream()
                .filter(el -> el % 2 == 0)
                .map(el -> 3 * el)
                .forEach(System.out::println);

    }
}
