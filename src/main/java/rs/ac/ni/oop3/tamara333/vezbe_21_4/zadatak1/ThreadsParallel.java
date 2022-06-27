package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak1;

import java.util.Random;

public class ThreadsParallel {
    public static void main(String[] args) {
        Thread thread = new MyThread(4);
        thread.start();

    }
}
