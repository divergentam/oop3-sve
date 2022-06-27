package rs.ac.ni.oop3.tamara333.FileDownloader;

public class UnsupportedFileTypeException extends Exception{
    public UnsupportedFileTypeException() {
        super("There is no file type defined!");
    }

    public UnsupportedFileTypeException(String fileType) {
        super("File type " + fileType + " is not allowed!");
    }
}
