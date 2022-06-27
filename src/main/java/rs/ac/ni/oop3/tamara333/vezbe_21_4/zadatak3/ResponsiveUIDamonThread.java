package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak3;

import lombok.Getter;

@Getter
public class ResponsiveUIDamonThread extends Thread {
    private volatile double d = 1;
    // nikad u threadu ne treba da se radi uois podataka u bazu
    // jer onda kada main Thread zavrsi sa radom zavrsava i DamonThread
    // DamonThread-ovi mogu da se koriste za nesto sto nije od sustinske vaznosti
    public ResponsiveUIDamonThread(){
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while(true){
            d = (d + (Math.PI + Math.E)) / d;
        }
    }
}
