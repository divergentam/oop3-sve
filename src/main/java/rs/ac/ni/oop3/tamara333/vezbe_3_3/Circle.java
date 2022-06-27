package rs.ac.ni.oop3.tamara333.vezbe_3_3;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

public class Circle extends Figure {
    private final double r;

    @Override
    public double area(){
        return r * r * Math.PI;
    }

    @Override
    public double circumference() {
        return 2 * r * Math.PI;
    }

 /*   @Override
    public String toString() {
        return String.format("Circle(%s, r = %4.2f)\t\t\t\t", label, r);
    }*/
}
