package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak3;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ResponsiveUI extends Thread {

        private volatile double d = 1;

        @Override
        public void run() {
            while (true) {
                d = d + (Math.PI + Math.E) / d;
                if (Thread.interrupted()) { // moze Thread.currentThread.isInterrupted() - ne menja fleg
                    return;
                }

            }
    }
}
