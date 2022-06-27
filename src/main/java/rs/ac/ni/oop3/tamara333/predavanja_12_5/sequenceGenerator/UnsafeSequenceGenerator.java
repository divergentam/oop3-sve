package rs.ac.ni.oop3.tamara333.predavanja_12_5.sequenceGenerator;

public class UnsafeSequenceGenerator implements SequenceGenerator {
    private int value;

    @Override
    public int getAndIncrease() {
        // RACE CONDITION
        // return value ++; // javljaju se dupli brojevi dok se neki brojevi uopste ne javljaju npr 93 .. 99
        // to nije jedna komanda - imamo u implementaciji 2 stvari
        // u jednom treuntku moze da se desi da dva threada u isto vreme pristupe value i povecaju je za 1
        // ukoliko je prethodna vrednost bila 8 nova vrednostce u oba slucaja biti 9
        // sto je lose jer bi svaki thread trebao da poveca broj za 1 i na kraju da imamo 10
        // npr za 10 000 br izgubili bi oko 500 brojeva
        // SINHRONIZACIJA
        // da samo jedan Thread moze da pokreene ovaj kod u datom trenutku
        // ako se neka promenljiva menja onda moramo da organizujemo pristup toj promenljivoj

        int currentValue = value;
        value += 1;
        return currentValue;
    }
}
