package rs.ac.ni.oop3.tamara333.vezbe_31_3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Downloader {
    protected static void printFileSize(FileDownloader fileDownloader3, URL url){
        try {
            System.out.print(fileDownloader3.getFileSize(url) + "\n");
        } catch (IOException e) {
            Path path = Path.of(url.getPath());
            log.error("File " + path.getFileName() + " cannot be downloaded!" + "\n");
        }
    }

    public static void main1(String[] args) throws IOException {
        final FileDownloader fileDownloader= new FileDownloader();

        final List<String> lines = fileDownloader
            /*.readLines("D:\\II god\\Objektno orjentisano programiranje 3\\03.03. - Figure\\src\\main\\resources\\log4j.properties");*/
                /*.readLines("c:\\letters.txt");*/
                .readLines("/brojevi.txt");

        for(final String line : lines){
            System.out.println(line);
        }
    }

    public static void main2(String[] args) throws IOException {
        final FileDownloader fileDownloader = new FileDownloader();

/*
        fileDownloader.readLines("/links.txt")
                .stream()
                .map(fileDownloader2::getLink)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);*/

        fileDownloader.readLines("/links3.txt")
                .stream()
                .map(fileDownloader::getLink)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(fileDownloader::downloadFile);

    }

    public static void main(String[] args) {
        final CommandLineParser commandLineParser = new DefaultParser();

        final Options options = defineOptions();

        try {
            final CommandLine commandLine = commandLineParser.parse(options, args);

            if(commandLine.hasOption("v")){
                System.out.println("Option --verbose is turned on");
            }

            if(commandLine.hasOption("a")){
                String[] allowedTypes = commandLine.getOptionValue('a').split(",");
                for(final String allowedType : allowedTypes){
                    System.out.println(allowedType);
                }
            }
        } catch (ParseException e) {
            log.error("Failed to parse options. Error: {}", e.getMessage());

            final HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("java Downloader [<option>] \n Avalible options:", options);
        }

    }

    private static Options defineOptions(){
        final Options options = new Options();

        options.addOption("v", "verbose", false, "If its turned off download will be shown");

        options.addOption(Option.builder()
                    .option("f")
                    .longOpt("force-save")
                    .desc("Force file overwrite")
                    .hasArg(false)
                .build());

        options.addOption(Option.builder()
                        .option("a")
                        .longOpt("allowed-types")
                        .hasArg(true)
                        .valueSeparator(',')
                        .desc("Have a arguments that defines allowed types comma separated list")
                .build());

        options.addOption(Option.builder()
                        .option("h")
                        .longOpt("help")
                        .hasArg(false)
                        .desc("Shows help")
                .build());


        return options;
    }
}
