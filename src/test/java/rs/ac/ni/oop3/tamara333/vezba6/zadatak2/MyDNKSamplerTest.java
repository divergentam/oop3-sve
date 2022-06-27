package rs.ac.ni.oop3.tamara333.vezba6.zadatak2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDNKSamplerTest {
    public String dnk = "A";
    public MyDNKSampler sampler = new MyDNKSampler(dnk);

    @Test
    public void shouldCompAddNuc() throws DNKException {
        sampler.addNucleotide('A');
        sampler.addNucleotide('A');
        sampler.addNucleotide('A');
        sampler.addNucleotide('C');

        String result = "AAAAC";
        Assertions.assertEquals(sampler.getDnk() , result);

    }

}