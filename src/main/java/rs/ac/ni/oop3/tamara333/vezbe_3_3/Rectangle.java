package rs.ac.ni.oop3.tamara333.vezbe_3_3;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.oop3.tamara333.vezbe_3_3.Figure;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@SuperBuilder

public class Rectangle extends Figure {
    private double a;
    private double b;

    @Override
    public double area() {
        return a * b;
    }

    @Override
    public double circumference() {
        return 2 * (a + b);
    }

 /*   @Override
    public String toString() {
        return String.format("Rectangle(%s, a = %4.2f, b = %4.2f)", label, a, b);
    }*/
}
