package rs.ac.ni.oop3.tamara333.predavanja_8_4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerRunnable implements Runnable {

    private final int n;

    public WorkerRunnable(int n) {
        this.n = n;
    }

    @Override
    public void run() { // metod koji nista ne trazi ii nista ne vraca samo radi nesto
        for(int i = 0; i< n; i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                log.error("Interrupted");
                return;
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
