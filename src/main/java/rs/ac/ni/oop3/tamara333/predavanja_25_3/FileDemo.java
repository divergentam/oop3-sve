package rs.ac.ni.oop3.tamara333.predavanja_25_3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        final Path filePath = Paths.get("letters.txt");
        final Path secondPath = Paths.get("temp", "..", "letters.txt");
        final Path dirPath = Paths.get("src", "main");
        final Path badFilePath = Paths.get("temp","letters.txt");
        final Path fileCopy2 = Paths.get("letters-copy.txt");

            Files.copy(filePath, fileCopy2, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(fileCopy2);

        final String tempDir = System.getProperty("user.home");

        Path tempPath = Files.createTempFile(Paths.get(tempDir), "temp_file",".tmp");
        System.out.println(tempPath.toAbsolutePath());
        tempPath.toFile().deleteOnExit();

        try(BufferedWriter out = Files.newBufferedWriter(tempPath)) {
            out.write("Temporary file content: ");
            out.newLine();
        }

        List<String> lines = Files.readAllLines(tempPath, StandardCharsets.UTF_8);
        for(String line : lines){
            System.out.println(line);
        }
/*
        final Path fileCopy = Paths.get("letters-copy.txt");
        final Path fileCopy2 = Paths.get("letters-copy.txt");

        try {
            copyChars(filePath, fileCopy2);
        } catch (FileException e) {
            e.printStackTrace();
        }*/

    }

    private static void copyChars(Path filePath, Path fileCopy) throws FileException {
        try(final FileReader fileReader= new FileReader(String.valueOf(filePath));
            final FileWriter fileWriter = new FileWriter(String.valueOf(fileCopy))) {

            int c;
            int count = 0;

            while((c = fileReader.read())!= -1){
                fileWriter.write(c);
                count++;
            }
            System.out.println("\ncopyChars - Total reads:" + count);

        }
        catch (IOException e) {
            throw new FileException("IOException occurred", e.getCause());
        }
    }

    public static void mainFiles(String[] args) {
        final Path filePath = Paths.get("letters.txt");
        final Path secondPath = Paths.get("temp", "..", "letters.txt");
        final Path dirPath = Paths.get("src", "main");
        final Path badFilePath = Paths.get("temp","letters.txt");

        if(Files.exists(filePath)){
            System.out.println("File " + filePath + " exists");
        }

        if(Files.notExists(badFilePath)){
            System.out.println("File " + badFilePath + " does not exists");
        }

        System.out.println(Files.isDirectory(dirPath));
        System.out.println(Files.isRegularFile(dirPath));

        try {
            System.out.println(Files.isSameFile(filePath, secondPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
