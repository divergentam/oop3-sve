package rs.ac.ni.oop3.tamara333.predavanja_25_3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path path1 = Paths.get("brojevi.txt");
        Path path2 = Paths.get("letters.txt");

        try {
            System.out.println(path1.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(path2.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main_RelativizeAndNormalize(String[] args) {
        Path path1 = Paths.get("src", "main", "java").toAbsolutePath();
        Path path2 = Paths.get("target", "java").toAbsolutePath();

        System.out.println(path1);
        System.out.println(path2);

        System.out.println(path1.relativize(path2));

        Path path3 = Paths.get("test", "..", "..", "data");
        System.out.println(path3.toAbsolutePath().normalize());
    }
    public static void mainHomeDir(String[] args) {
        final String homeDir = System.getProperty("user.home");
        System.out.println(homeDir);
        final Path tempDir = Paths.get(homeDir, "temp");
        System.out.println(tempDir);
    }
    public static void mainPath(String[] args) {
        Path path = Paths.get("temp", "data", "numbers.txt");
        System.out.println("Ansolute path: " + path.toAbsolutePath());
        System.out.println("File name in path: " + path.getFileName());
        System.out.println("________________________________________");

        final Path absolutePath = path.toAbsolutePath();
        for (int i = 0; i< absolutePath.getNameCount(); i++) {
            System.out.println(absolutePath.getName(i));
        }
        System.out.println("________________________________________");

        for(final File file : File.listRoots()){
            System.out.println(file);
        }
    }
}
