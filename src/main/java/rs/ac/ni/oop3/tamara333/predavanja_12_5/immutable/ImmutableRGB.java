package rs.ac.ni.oop3.tamara333.predavanja_12_5.immutable;

import lombok.Value;

@Value // final klasa i sva polja unutar su final
public class ImmutableRGB {
    int red;
    int green;
    int blue;
    String name;

    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(
                255 - red,
                255 - green,
                255 - blue,
                "Inverse of " + name
        );
    }
}
