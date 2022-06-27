package rs.ac.ni.oop3.tamara333.vezbe_24_3;

import java.awt.desktop.FilesEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ovo radimo samo kada radimo sa bajtovima
public class StreamDemo {
    public static void main1(String[] args) {
        final InputStream is = StreamDemo.class.getClassLoader().getResourceAsStream("brojevi.txt");

        if (is == null) {
            System.out.println("Fajl brojevi.txt nije pronadjen");
            return;
        }

        Scanner scanner = new Scanner(is);

        int x = scanner.nextInt();
        System.out.println("Ucitan broj: " + x);

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static final int MAX_BYTES = 100;

    private static final String inFileName = "sample.txt";
    private static final String outFileName = "sample-copy.txt";

    public static void main(String[] args) {
        try{
            copyBytes(inFileName, outFileName);
            copyChars(inFileName, outFileName);
        } catch (NotValidFileNameException e){
            final Throwable cause = e.getCause();
            e.printStackTrace();
        }

    }

    private static void copyChars(final String inFileName,final String outFileName) throws NotValidFileNameException {
        try(final FileReader reader= new FileReader(inFileName);
            final FileWriter writer = new FileWriter(outFileName)){
            int c;
            int count = 0;

            while((c = reader.read())!= -1){
                writer.write(c);
                count++;
            }
            System.out.println("copyChars - Total reads:" + count);
        }
        catch (IOException e){
            throw new NotValidFileNameException("copy Chars - This file name is not valid or this file not exists ", e);
        }
    }

    private static void copyBytes(final String inFileName, final String outFileName) throws NotValidFileNameException {

        //ovo necu da radim ovako zbog Exceptiona vec koristim try with resources - ne treba nam rucni close
        try (final FileInputStream in = new FileInputStream(inFileName);
             final FileOutputStream out = new FileOutputStream(outFileName)) {
            System.out.println("copyBytes - total reads: " + copyBytePerByte(in, out));
            //System.out.println("copyUsingBuffer - total reads: " + copyUsingBuffer(in, out));
        }catch (IOException e){
            throw new NotValidFileNameException("copy Bytes - This file name is not valid or this file not exists ", e);
        }
    }

    //malo brze resenje
    private static int copyUsingBuffer(final FileInputStream in, final FileOutputStream out) throws IOException {
        byte[] buffer = new byte[MAX_BYTES]; // ima velicinu od hiljadu bajtova
        int counter = 0;
        int bytesRead; // broj procitanih

        while ((bytesRead = in.read(buffer)) != -1) { // zar nije kraj kad ucitamo 0 bajtova?
            // -- omogucava da i kada se ne procita dovolljno podataka nastavlja sa pokusajima
                /*
                niko nam ne garantuje da cemo mi svaki put da ucitamo 1000 bajtova
                pa kad imamo manje zauzece mesto za do 1000
                pa zato ne pisemo ---- out.write(buffer);
                vec koristimo     ---- out.write(buffer, 0, bytesRead); | stampa od 1 el do onliko el koje je procitao
                */
            out.write(buffer, 0, bytesRead);
            counter++;
        }
        return counter;
    }

    private static int copyBytePerByte(FileInputStream in, FileOutputStream out) throws IOException {
        int c;
        int counter = 0;
        while ((c = in.read()) != -1) {
            out.write(c);
            ++counter;
        }
        return counter;
    }
    /*   // ako hocu da binarno kopiram fajl - da ucitavam i upisujem bajt po bajt

        int c; //vrednost ucitavana iz Input streama
        while ((c = in.read()) != -1) {
            out.write(c);
        }
    */

    /*
    *** final FileInputStream in = new FileInputStream("sample.txt");
        final FileOutputStream out = new FileOutputStream("sample-copy.txt");
    *** fileInputStream.readAllBytes(); puca ako nema dovoljno memorije
    *** final FileOutputStream out = new FileOutputStream("bytes.dat");
        ne moze da se upise broj koji zauzima vise od jednog bajta npr 256
        out.write(256); - ignorise visi bajt
    */
}