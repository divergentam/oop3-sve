package rs.ac.ni.oop3.tamara333.vezba6.zadatak2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
@Slf4j
public class MyDNKSampler {
    private String dnk;
    private final static int SIZE = 100;
    private final Set<String> forbiddenSubstrings = Set.of("CCCCC", "AAAAA", "TTTTT", "GGGGG");

    public MyDNKSampler(String DNK) {
        this.dnk = DNK;
    }

    private boolean checkRepetition(String s) {
        for (String forbidden : forbiddenSubstrings) {
            if (s.contains(forbidden))
                return true;
        }
        return false;
    }

    public boolean checkLength(String s) {
        return s.length() < SIZE - 1;
    }

    public void addNucleotide(char c) throws DNKException {
        c = Character.toUpperCase(c);

        if (!isLegalCharacter(c)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.UNSUPPORTED_NUCLEOTIDE,
                    "You cant add that nucleotide, because its invalid!");
        }

        if (!checkLength(dnk)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.NO_MORE_SPACE,
                    "You cant add more nucleotide, DNK is already full!");
        }
        String s = dnk + c;

        if (checkRepetition(s)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.REPETITION_ERROR,
                    "You cant add this nucleotide because of repetition error! ");
        }

        dnk = s;
    }

    public List<Character> convertToList(String s) {
        List<Character> list = new ArrayList<>();

        for (char ch : dnk.toCharArray()) {
            list.add(ch);
        }

        return list;
    }

    public int checkNumberOfRepetition(char c) {
        List<Character> list = convertToList(dnk);

        return (int) list.stream()
                .filter(i -> i == c)
                .count();
    }

    public void cut(int i, int j) throws DNKException {
        if (i < 0) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.INDEX_OUT_OF_BOUNDARIES,
                    "Cant cut DNK because index i is out of boundaries! ");
        }

        if (dnk.length() < j) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.INDEX_OUT_OF_BOUNDARIES,
                    "Cant cut DNK because index j is out of boundaries! ");
        }

        String newDnk = dnk.substring(0, i) + dnk.substring(j) + dnk.substring(i, j);

        if (checkRepetition(newDnk)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.REPETITION_ERROR,
                    "You cant add this nucleotide because of repetition error! ");
        }

        dnk = newDnk;
    }

    public Set<Character> getAvailable() {
        return Set.of('C', 'G', 'A', 'T');
    }

    public boolean isLegalCharacter(char c) {
        return getAvailable().contains(c);
    }

    public void delete(int k, char c) throws DNKException {
        c = Character.toUpperCase(c);
        if (!isLegalCharacter(c)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.NOT_LEGAL_NUCLEOTIDE,
                    "Character " + c + " is not legal nucleotide!");
        }

        if (checkNumberOfRepetition(c) < k) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.NOT_ENOUGH_NUCLEOTIDES,
                    "In DNK there is no " + k + " nucleotides " + c + "!");
        }

        char[] list = dnk.toCharArray();
        int count = 0;
        StringBuilder newDnkBuilder = new StringBuilder();

        for (char ch : list) {
            if (count < k && ch == c) {
                count++;
            } else {
                newDnkBuilder.append(ch);
            }
        }
        String newDnk = newDnkBuilder.toString();
        if (!checkRepetition(newDnk)) {
            log.info("DNK so far: " + dnk);
            throw new DNKException(DNKException.DnkErrorCode.REPETITION_ERROR,
                    "You cant add this nucleotide because of repetition error! ");
        }

        dnk = newDnk;
    }

    public static void main(String[] args) throws DNKException {
        final MyDNKSampler dnkSampler = new MyDNKSampler("ACTA");
        dnkSampler.addNucleotide('a');
        dnkSampler.addNucleotide('a');
        dnkSampler.addNucleotide('a');
        dnkSampler.addNucleotide('g');
        System.out.println(dnkSampler.getDnk());
        dnkSampler.cut(0, 3);
        System.out.println(dnkSampler.getDnk());
        dnkSampler.delete(3, 'A');
        dnkSampler.delete(1, 'g');
        System.out.println(dnkSampler.getDnk());
    }

}
