package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public class MyThread extends Thread{

    private final int n;
    private Random[] array;
    private final Random random = new Random();

    @Override
    public void run() {
        for(int i =0; i < n; i++){
            array[i] = random;
        }
    }

    Random[] getArray(){
        return array;
    }
}
