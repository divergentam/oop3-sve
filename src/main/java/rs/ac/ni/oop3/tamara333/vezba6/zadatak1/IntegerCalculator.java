package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import java.util.Set;

public class IntegerCalculator implements Calculator<Integer, Integer> {
    protected Integer first;
    protected Integer second;
    protected Character operation;


    @Override
    public void addOperand(Integer operand) throws CalculatorException {
        if (first == null) {
            first = operand;
        } else if (second == null) {
            second = operand;
        } else {
            throw new CalculatorException(CalculatorException.ErrorCode.TOO_MANY_OPERANDS, "Too many operands!");
        }
    }

    @Override
    public void removeLastOperand() throws CalculatorException {
        if (second != null) {
            second = null;
        } else if (first != null) {
            first = null;
        } else {
            throw new CalculatorException(CalculatorException.ErrorCode.NO_OPERANDS, "No more operands to remove! ");
        }
    }

    @Override
    public void setOperation(Character operation) throws CalculatorException {
        if (!getAvailableOperations().contains(operation)) {
            throw new CalculatorException(CalculatorException.ErrorCode.UNKNOWN_OPERATION,
                    "Operation " + operation + " is not supported!");
        }

        this.operation = operation;
    }

    @Override
    public Set<Character> getAvailableOperations() {
        return Set.of('+', '-', '*', '%', '/');
    }

    @Override
    public Integer calculate() throws CalculatorException {
        if (operation == null) {
            throw new CalculatorException(CalculatorException.ErrorCode.OPERATION_NOT_DEFINED, "Operation must be defined!");
        }

        if (first == null || second == null) {
            throw new CalculatorException(CalculatorException.ErrorCode.MISSING_OPERAND, "Both operands must be defined!");
        }

        switch (operation) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '%':
                assertDivisionIsPossible();
                return first % second;
            case '/':
                assertDivisionIsPossible();
                return first / second;
            default:
                throw new CalculatorException(CalculatorException.ErrorCode.UNKNOWN_OPERATION,
                        "Operation " + operation + " is not defined!");
        }
    }

    private void assertDivisionIsPossible() throws CalculatorException {
        if(second == 0){
            throw new CalculatorException(CalculatorException.ErrorCode.SECOND_IS_ZERO, "Division by zero!");
        }
    }

}
