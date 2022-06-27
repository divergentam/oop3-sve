package rs.ac.ni.oop3.tamara333.predavanja_12_5.sequenceGenerator;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class SequenceWorker extends Thread {
    private final SequenceGenerator _sequenceGenerator;
    private final String _name;
    private final int _count;

    @Override
    public void run() {
        try (final BufferedWriter out = new BufferedWriter(new FileWriter("sequence_" + _name +  ".out"))) {
            for (int i = 0; i < _count; i++) {
                out.write(String.format("%d ", _sequenceGenerator.getAndIncrease()));

                if (i % 10 == 9) {
                    out.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
