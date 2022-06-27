package rs.ac.ni.oop3.tamara333.vezbe_31_3;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class FileDownloader {
    final String CONST = "";

    private static final int  MAX_BUFFERED_SIZE = 128;
    private static int COUNTER = 0;

    public List<String> readLines(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        boolean found = true;

        if(!Files.exists(path)){
            path = path.getFileName();
            log.debug("File not found using given path, checking current dir for file {}", path);
            if(!Files.exists(path)){
                log.debug("File not found in current dir, checking within resources");
                found = false;
            }
        }
        //ako je pronadjen imacu putanju do fajla
        //ako nije pronadjend path ce biti ime fajla

        final InputStream inputStream =
                found ? new FileInputStream(path.toFile()) : FileDownloader.class.getResourceAsStream(fileName);
        if(inputStream == null){
            throw new InputStreamNotMade("InputStream cannot be made ");
        }
        final List<String> result = new ArrayList<>();

        try(final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while((line = reader.readLine())!= null){
                if(!CONST.equals(line.trim())){
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputStream.close();

        return result;
    }

    public List<URL> returnURLs(List<String> listOfStrings){

        List<URL> result = new ArrayList<>();

        for(final String entry : listOfStrings){
            try {
                result.add(new URL(entry));
               /* final URL url = new  URL(entry);
                result.add(url);*/
            } catch (MalformedURLException e) {
                log.error("Entry `{}` is not a valid URL, ignoring it", entry);
            }
        }
        return result;
    }

    /*DRUGI NACIN*/
    public Optional<URL> getLink(final String entry){
        try {
            final URL url = new URL(entry);
            return Optional.of(url);
        } catch (MalformedURLException e) {
            log.error("Entry `{}` is not a valid URL, ignoring it", entry);
        }

        return Optional.empty();
    }

    public List<URL> getLinks(List<String> listOfStrings){

        return listOfStrings.stream()
                .map(this::getLink)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        /*
        return listOfStrings.stream()
                .map(entry -> getLink(entry))
                .filter(optionalURL -> optionalURL.isPresent())
                .map(optionalURL -> optionalURL.get())
                .collect(Collectors.toList());*/

       /* List<URL> result = new ArrayList<>();

        for(final String entry : listOfStrings){
            final Optional<URL> optionalURL = getLink(entry);
            if(optionalURL.isPresent()) {
                result.add(optionalURL.get());
            }
        }
        return result;
        */
    }

    private Set<String> allowedTypes = Set.of("pdf", "txt", "doc", "docx");

    public String checkFileType(URL url) throws UnsupportedFileTypeException {
        //.getFile() - daje nam putanju do resursa koji se nalazi na adresi
        final Path path = Paths.get(url.getFile());
        final String fileName = path.getFileName().toString();

        final int dotPosition = fileName.lastIndexOf('.'); // vraca index poslednje tacke na koju naidje
        if(dotPosition == -1){
            throw new UnsupportedFileTypeException();
        }
        final String type = fileName.substring(dotPosition + 1).toLowerCase(Locale.ROOT);

        if(!allowedTypes.contains(type)){
            throw new UnsupportedFileTypeException(type);
        }

        return fileName;
    }

    public int getFileSize(final URL url) throws IOException {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection(); //ovo moze da pukne
            connection.setRequestMethod("HEAD"); /*fajl se ne skida samo se dobija njnegova vel*/
            connection.getInputStream(); // tek tada se uspostavlja konekcija

            return connection.getContentLength();
        }
        finally {
            if(connection != null){
                connection.disconnect();
            }
        }
    }

    public void downloadFile(final URL url){
        final int fileSize;
        final String fileName;
        try{
            fileName = checkFileType(url);
        }
        catch (UnsupportedFileTypeException e){
            log.error(e.getMessage());
            return;
        }
        String resolvedFileName = fileName;
        while(Files.exists(Paths.get(resolvedFileName))){
            COUNTER++;
            int dotPos = resolvedFileName.lastIndexOf('.');
            final String type = resolvedFileName.substring(dotPos + 1).toLowerCase(Locale.ROOT);
            resolvedFileName = resolvedFileName.substring(0, dotPos) + COUNTER  + '.' + type;
        }

        try{
            fileSize = getFileSize(url);
        }
        catch(final IOException e){
            log.error("Cannot download file " + fileName + " from " + url.toString()  + "\n");
            return;
        }

        try(final InputStream inputStream = new BufferedInputStream(url.openStream());
            final BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(String.valueOf(resolvedFileName))))
        {
                final byte[] buffer = new byte[MAX_BUFFERED_SIZE];
                int bytesRead = 0;
                int totalDownloaded = 0;

                while((bytesRead = inputStream.read(buffer, 0, MAX_BUFFERED_SIZE)) != -1){
                    bufferedOutputStream.write(buffer, 0 , MAX_BUFFERED_SIZE);
                    totalDownloaded += bytesRead;
                    log.info("Link {} ---- Downloaded so far {} / {} bytes", url, totalDownloaded, fileSize);
                }

        } catch (IOException e) {
           log.error("Failed to download file " + resolvedFileName + " from url {}", url );
        }
    }

}
