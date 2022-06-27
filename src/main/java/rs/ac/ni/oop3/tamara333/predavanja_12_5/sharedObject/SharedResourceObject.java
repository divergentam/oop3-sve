package rs.ac.ni.oop3.tamara333.predavanja_12_5.sharedObject;

import java.util.concurrent.locks.Lock;

public class SharedResourceObject {

    private final Object lockA = new Object();
    private final Object lockB = new Object();

    private int valueA;
    private int valueB;
    private int valueC;

    // Nije dobro da se stavi synchronized na metodu jer onda dva dela koda koja su nezavisna zakljucavamo istim kljucem
    // pa onda ne mozemo da ih izvrsavamo paralelno

    public int methodA(){
        int currentValue;

        synchronized (lockA)
        {
            System.out.println("Executing method A");
            sleepForInt(2000);
            currentValue = valueA;
            valueA++;
            System.out.println("Finished method A");

        }

        return currentValue;
    }
    public int methodB(){
        int currentValue;

        synchronized (lockB){
            System.out.println("Executing method B");
            sleepForInt(2000);
            currentValue = valueB;
            valueB++;
            System.out.println("Finished method B");

        }
        return currentValue;
    }

    public int methodC(){
        System.out.println("Executing method C");
        sleepForInt(2000);
        System.out.println("Finished method C");
         return valueC++;
    }

    private static void sleepForInt(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
