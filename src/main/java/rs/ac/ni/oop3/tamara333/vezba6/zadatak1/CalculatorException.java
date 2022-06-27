package rs.ac.ni.oop3.tamara333.vezba6.zadatak1;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
@Getter
public class CalculatorException extends  Exception{
    final ErrorCode errorCode;

    public CalculatorException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
        logInFile();
    }

    public void logInFile() {
        log.error("Type: {}\tMessage: {}",getErrorCode(),getMessage());
    }

    public enum ErrorCode{
        TOO_MANY_OPERANDS,
        NO_OPERANDS,
        UNKNOWN_OPERATION,
        OPERATION_NOT_DEFINED,
        MISSING_OPERAND,
        SECOND_IS_ZERO
    }
}
