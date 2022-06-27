package rs.ac.ni.oop3.tamara333.predavanja_8_4;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class WorkerThread extends Thread {

    private final int n;
    private final Random random = new Random();

    @Override
    public void run() {
        for(int i = 0; i< 5; i++) {
            try {
                Thread.sleep(500 + random.nextInt(500));
                // metod koji zaustavlja izvrsenje koda ali i u potpunosti blokira pristup Thread-u
                // u smislu da drugi kod nece moci da se izvrsava na ovom Thread-u
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
