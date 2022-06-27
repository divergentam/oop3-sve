package rs.ac.ni.oop3.tamara333.predavanja_18_3;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class ExceptionDemo {

    public static void main(String[] args) {
        try {
            new ListOfNumbers().generateList("numbers.txt.");
        } catch (ListCreationException e) {
            final Throwable cause = e.getCause();
            cause.printStackTrace();
            /*final StackTraceElement[] stackTraceElements= e.getStackTrace();
           for(final StackTraceElement stackTraceElement : stackTraceElements){
               stackTraceElement.
            }
            */
        }
    }

    public static void main2(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        int x, y;

        try {
            x = scanner.nextInt();
            y = scanner.nextInt();
        }
        catch(final InputMismatchException e){
            log.error("Bad input. Aborting...", e);
            return;
        }

        try{
            // log.info({} / {} = {}, x, y, x / y ); - loger ne treba da prikazuje izlaz iz programa
            System.out.format("%d / %d = %d%n", x, y, x / y);
            System.out.println("Comutation finished");
        }
        catch(final ArithmeticException e){
            System.out.println("Error! " + e.getMessage());
            return;
            //e.printStackTrace(); detalno gde se javila
        }
        finally {
            System.out.println("Executing finally block");
        }

        try{
            FileOutputStream fileOutputStream = new FileOutputStream("predavanja_18_3.txt");
        }
        catch(final FileNotFoundException e){
            log.error("File cann't be created ",e);
        }
    }
}
