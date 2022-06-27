package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ac.ni.oop3.tamara333.vezba6.zadatak1.CalculatorException;
import rs.ac.ni.oop3.tamara333.vezba6.zadatak1.PowerCalculator;

import static org.junit.jupiter.api.Assertions.*;

class owerCalculatorTest {

    @Test
    public void shouldComputerPower() throws CalculatorException {
        PowerCalculator powerCalculator = new PowerCalculator();
        powerCalculator.addOperand(2);
        powerCalculator.addOperand(4);
        powerCalculator.setOperation('^');

        Integer result = powerCalculator.calculate();

        Assertions.assertEquals(16, result);
    }

    @Test
    public void shouldThrowUnknownException() throws CalculatorException{
        PowerCalculator powerCalculator = new PowerCalculator();
        powerCalculator.addOperand(2);
        powerCalculator.addOperand(4);
        powerCalculator.setOperation('!');

        powerCalculator.calculate();

    }
}
