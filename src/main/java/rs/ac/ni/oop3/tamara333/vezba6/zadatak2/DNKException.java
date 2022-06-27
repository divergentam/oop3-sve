package rs.ac.ni.oop3.tamara333.vezba6.zadatak2;

public class DNKException extends Exception{

    final DnkErrorCode errorCode;

    public DNKException(DnkErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public enum DnkErrorCode{
        REPETITION_ERROR,
        NO_MORE_SPACE,
        NOT_ENOUGH_NUCLEOTIDES,
        NOT_LEGAL_NUCLEOTIDE,
        UNSUPPORTED_NUCLEOTIDE,
        INDEX_OUT_OF_BOUNDARIES
    }

}
