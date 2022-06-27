package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak3;

import lombok.Getter;

@Getter
public class UnresponsiveUI {
    private double d = 1;

    public UnresponsiveUI(){
        while(d > 0){
            d = (d + (Math.PI + Math.E)) / d;
        }
        // ovo nikad nece da se izvrsi
    }
}
