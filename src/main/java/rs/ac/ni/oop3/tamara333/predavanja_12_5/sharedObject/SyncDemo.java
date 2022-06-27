package rs.ac.ni.oop3.tamara333.predavanja_12_5.sharedObject;

import javax.print.attribute.standard.SheetCollate;

public class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        final SharedResourceObject shared = new SharedResourceObject();
        final Thread t1 = new Thread(()-> {
            for(int i = 0; i< 3; i++) shared.methodA();
        });
        final Thread t2 = new Thread(()-> {
            for(int i = 0; i< 3; i++) shared.methodB();
        });
        final Thread t3 = new Thread(()-> shared.methodC());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}
