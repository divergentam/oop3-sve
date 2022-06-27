package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import rs.ac.ni.oop3.tamara333.vezba6.zadatak1.CalculatorException;

import java.util.Set;

public interface Calculator<R, T> {
    void addOperand(T operand) throws CalculatorException;

    void removeLastOperand() throws CalculatorException;

    void setOperation(Character operation) throws CalculatorException;

    Set<Character> getAvailableOperations();

    R calculate() throws CalculatorException;

    public static void main(String[] args) {

        final IntegerCalculator calculator= new IntegerCalculator();

        try {
            calculator.addOperand(3);
            calculator.addOperand(5);
            calculator.setOperation('s');
            Integer result = calculator.calculate();
            System.out.println(result);
        } catch (CalculatorException e) {
            e.printStackTrace();
        }
    }
}
