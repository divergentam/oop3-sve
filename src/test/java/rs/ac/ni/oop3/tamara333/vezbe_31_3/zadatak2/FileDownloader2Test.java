package rs.ac.ni.oop3.tamara333.vezbe_31_3.zadatak2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ac.ni.oop3.tamara333.vezbe_31_3.FileDownloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class FileDownloader2Test {
    @Test
    public void shouldComputerDo() throws IOException, MalformedURLException {
        final FileDownloader fileDownloader = new FileDownloader();

        final List<String> list = fileDownloader.readLines("links.txt");
        final List<URL> urls = fileDownloader.returnURLs(list);

        for(final URL url : urls){
            System.out.println(url);
        }

        final List<String> validURLs = List.of("https://www.google.com","https://www.pmf.ni.ac.rs/predmeti/materijali/?idp=7063", "https://rs.oriflame.com/products/digital-catalogue-current?PageNumber=1", "https://rs.oriflame.com/products/digital-catalogue-current?PageNumber=785");

        final List<URL> listURL = new ArrayList<>();
        for(final String valid : validURLs) {
            listURL.add(new URL(valid));
        }

        Assertions.assertEquals(listURL, urls);

    }




}