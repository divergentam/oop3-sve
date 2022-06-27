package rs.ac.ni.oop3.tamara333.predavanja_18_3;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.predavanja_18_3.BadValueException;
import rs.ac.ni.oop3.tamara333.predavanja_18_3.ForbiddenValueException;
import rs.ac.ni.oop3.tamara333.predavanja_18_3.ListCreationException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

@Slf4j
public class ListOfNumbers  {
    private static final int SIZE = 100;
    public static final Random RANDOM = new Random();

    /*OVDE METODA BACA EXCEPTION*/
    private void writeList(final String fileName) throws FileNotFoundException, BadValueException {
        /*Zasto ovde stavljamo u finally writer.close()
        * jer moze da se desi da u toku izvrsavanja for petlje pukne program
        * i u tom slucaju se nece zatvoriti writer.close()
        * to ne zelimo jer writer.close() je bitan jer sve
        * resurse koje je taj objekat zauzimao ocisti */

        try(final PrintWriter writer = new PrintWriter(fileName)) {
            for (int i = 0; i < SIZE; i++) {
                final int randomValue = RANDOM.nextInt(1000);

                if(100 <= randomValue && randomValue <= 500){
                    throw new BadValueException("Value " + randomValue + " is a bad value!");
                }

                if(500 <= randomValue && randomValue <= 900){
                    throw new ForbiddenValueException("Value " + randomValue + " is a forbiden value!");
                }

                writer.println((i + 1) + ": " + randomValue );
            }

        }

    }

    public void generateList(final String fileName) throws ListCreationException/*throws FileNotFoundException */{
      /*  writeList(fileName);*/
        try {
            writeList(fileName);
        }
        catch(final ForbiddenValueException | FileNotFoundException e){
            log.debug("Erorr: " + e.getMessage());
            throw new ListCreationException("Erorr creating list",e);
        }
        catch (final BadValueException e){
            log.debug("A bad value occurred! Erorr: " + e.getMessage());
            throw new ListCreationException("Erorr creating list",e);
        }
        /*catch(final BadValueException | FileNotFoundException e){
            System.out.println("Erorr: " + e.getMessage());
        }*/
      /*  catch (final Exception e){
            System.out.println("General error: "  + e.getMessage());
        }*/

        /* catch (final FileNotFoundException e) {
            System.out.println("Erorr writing numbers: \nErorr: " + e.getMessage());
        }
        catch(final ForbiddenValueException e){
            System.out.println("Error writing numbers! Error: " + e.getMessage());
        }*/
    }

}
