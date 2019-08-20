package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ContentExtractor {
    public static String getContentFromUrl(String urlString){
        try{
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            InputStream reader = connection.getInputStream();
            return new String(reader.readAllBytes());
        } catch (MalformedURLException urlException){
            System.out.println("Invalid url entered" + urlException);
        } catch (IOException connectionException) {
            System.out.println("Connection unable to establish");
        }
        return null;
    }

    public static List<String> getListFromFile(String filename) {
        List<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}

