package rs.ac.ni.oop3.tamara333.FileDownloader;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezbe_31_3.UnsupportedFileTypeException;

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

    public static final String EMPTY = "";
    private static final int  MAX_BUFFERED_SIZE = 128;
    public static int COUNT = 0;
    public static Set<String> allowedTypes= Set.of("txt", "pdf", "doc", "docx");

    public List<String> readLines(final String filePath) throws IOException {
        Path path = Paths.get(filePath);
        boolean found = true;

        if(!Files.exists(path)){
            path = path.getFileName();
            if(!Files.exists(path)){
                found = false;
            }
        }

        final InputStream inputStream = found ? new FileInputStream(path.toFile()) : FileDownloader.class.getResourceAsStream(filePath);

        if(inputStream == null){
            throw new FileNotFoundException("File " + filePath + " not found!");
        }

        final List<String> result = new ArrayList<>();
        try(final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while((line = reader.readLine()) != null){
                if(!EMPTY.equals(line.trim())){
                    result.add(line);
                }
            }
        }

        inputStream.close();
        return result;
    }

    public static Optional<URL> getLink(final String entry){
        try {
            URL url = new URL(entry);
            return Optional.of(url);
        } catch (MalformedURLException e) {
            log.error("This is not valid url + " + entry);
        }
        return Optional.empty();
    }

    public List<URL> getLinks(final List<String> entries){

       /* final List<URL> urls = new ArrayList<>();

        for(String entry : entries){
            Optional<URL> optionalURL = getLink(entry);
            optionalURL.ifPresent(urls::add);
        }
        return urls;*/

        return entries.stream()
                .map(FileDownloader::getLink)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

    }

    public String checkFileType(final URL url) throws UnsupportedFileTypeException {
        final Path path = Paths.get(url.toString());
        final String fileName = path.getFileName().toString();

        int dotIndex = fileName.lastIndexOf(".");

        if(dotIndex == -1){
            throw new UnsupportedFileTypeException();
        }

        String extension = fileName.substring(dotIndex + 1);

        if(!allowedTypes.contains(extension)){
            throw new UnsupportedFileTypeException(extension);
        }
        return fileName;
    }

    public int getFileSize(final URL url) throws IOException {
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("HEAD");
            urlConnection.getInputStream();

            return urlConnection.getContentLength();
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }

    public void download(final URL url){
        final int fileSize;
        final String fileName;

        try {
            fileName = checkFileType(url);
        } catch (UnsupportedFileTypeException e) {
            log.error(e.getMessage());
            return;
        }

        String resolvedFileName = fileName;
        Path path = Paths.get(resolvedFileName);
        while (Files.exists(path)){
                COUNT++;
                int dotPosition = fileName.lastIndexOf(".");
                final String extension = fileName.substring(dotPosition).toLowerCase(Locale.ROOT);
                resolvedFileName = fileName.substring(0, dotPosition) + COUNT + extension;
        }

        try {
            fileSize = getFileSize(url);
        } catch (IOException e) {
            log.error("Cannot download file " + fileName + " from " + url.toString()  + "\n");
            return;
        }

        try(final BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
            final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(String.valueOf(resolvedFileName))))
        {
            byte[] buffer = new byte[MAX_BUFFERED_SIZE];
            int bytesRead = 0;
            int totalDownload = 0;

            while((bytesRead = inputStream.read(buffer,0, MAX_BUFFERED_SIZE)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalDownload += bytesRead;
                log.info("Link {} ---- Downloaded so far {} / {} bytes", url, totalDownload, fileSize);

            }
        } catch (IOException e) {
            log.error("Failed to download file " + resolvedFileName + " from url {}", url );
        }

    }
}
