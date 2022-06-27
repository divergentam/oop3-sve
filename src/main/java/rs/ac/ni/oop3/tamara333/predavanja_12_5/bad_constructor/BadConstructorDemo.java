package rs.ac.ni.oop3.tamara333.predavanja_12_5.bad_constructor;

import java.util.ArrayList;
import java.util.List;

public class BadConstructorDemo {
    public static void main(String[] args) {
        final List<ObjectInstance> instances = new ArrayList<>();
        new Thread(()->{
            new ObjectInstance("1", instances);
        }).start();

        new Thread(()->{
            sleepFor(1000);
            instances.forEach(i ->{
                System.out.println(i.getLabel());
                System.out.println(i.getOtherLabel());
            });
        }).start();

        instances.forEach(i -> {
            System.out.println(i.getLabel());
            System.out.println(i.getOtherLabel());
        });

    }

    private static void sleepFor(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
