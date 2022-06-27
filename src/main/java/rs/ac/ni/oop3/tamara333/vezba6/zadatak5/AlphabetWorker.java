package rs.ac.ni.oop3.tamara333.vezba6.zadatak5;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class AlphabetWorker extends Thread {
    final String sentence;
    Map<Character, Integer> result = new HashMap<>();

    @Override
    public void run() {
        char[] sentenceArr = sentence.toCharArray();

        for(char c : sentenceArr){
            result.put(c, 0); // definisemo kljuceve i zadajemo im pocetne vrednosti
        }

        for(char c : sentenceArr){
            result.put(c, result.get(c) + 1); // definisemo kljuceve i zadajemo im pocetne vrednosti
        }

    }
}
