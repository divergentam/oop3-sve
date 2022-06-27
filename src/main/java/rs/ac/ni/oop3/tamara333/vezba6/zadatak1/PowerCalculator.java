package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import java.util.HashSet;
import java.util.Set;

public class PowerCalculator extends IntegerCalculator{
    @Override
    public Set<Character> getAvailableOperations(){
        Set<Character> currentOperations = new HashSet<>(super.getAvailableOperations());
        currentOperations.add('^');
        return currentOperations;
    }

    @Override
    public Integer calculate() throws CalculatorException {
        try{
            return super.calculate();
        }
        catch (CalculatorException e){
            if(e.getErrorCode() != CalculatorException.ErrorCode.UNKNOWN_OPERATION){
                throw e;
            }

            if(operation == '^'){
                return (int) Math.pow(first, second);
            }
            else{
                throw new CalculatorException(CalculatorException.ErrorCode.UNKNOWN_OPERATION,
                        "Unknown operation " + operation + "!");
            }
        }
    }
}
