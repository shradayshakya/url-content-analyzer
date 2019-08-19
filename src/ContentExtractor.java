import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ContentExtractor {
    public static String getContent(String urlString){
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
}
