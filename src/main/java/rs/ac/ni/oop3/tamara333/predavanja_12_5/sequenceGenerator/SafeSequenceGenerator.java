package rs.ac.ni.oop3.tamara333.predavanja_12_5.sequenceGenerator;

public class SafeSequenceGenerator implements SequenceGenerator{
    private int value;

    @Override
    public synchronized int getAndIncrease() {
        return value++;
    }
}
