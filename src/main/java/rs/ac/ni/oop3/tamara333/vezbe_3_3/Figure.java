package rs.ac.ni.oop3.tamara333.vezbe_3_3;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@SuperBuilder

public abstract class Figure implements Comparable<Figure>  {
    protected final String label;

    public abstract double area();
    public  abstract double circumference();

    @Override
    public int compareTo(Figure other) {
        return Double.compare(this.circumference(), other.circumference());
    }
}
