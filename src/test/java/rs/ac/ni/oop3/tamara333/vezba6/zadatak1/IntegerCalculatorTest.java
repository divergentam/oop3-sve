package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ac.ni.oop3.tamara333.vezba6.zadatak1.CalculatorException;
import rs.ac.ni.oop3.tamara333.vezba6.zadatak1.IntegerCalculator;

import static org.junit.jupiter.api.Assertions.*;

class IntegerCalculatorTest {

    @Test
    public void shouldComputerSum() throws CalculatorException {

        final IntegerCalculator calculator= new IntegerCalculator();

        calculator.addOperand(3);
        calculator.addOperand(5);
        calculator.setOperation('+');
        Integer result = calculator.calculate();

        Assertions.assertEquals(8, result);
    }
}