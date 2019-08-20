package components;

import utils.ContentExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WebCrawler {
    public void start(){
        long startTime = System.currentTimeMillis();

        Logger logger = Logger.getLogger(WebCrawler.class.getName());
        String[] keywords = { "oracle", "java"};
        List<String> urls = ContentExtractor.getListFromFile("src/urls.txt");
        List<UrlContentAnalyzer> urlThreads = new ArrayList<>();

        for (String url : urls){
            UrlContentAnalyzer urlThread = new UrlContentAnalyzer(url, keywords);
            urlThread.start();
            urlThreads.add(urlThread);
        }

        logger.info("*************************************************************");
        // To check if all the threads have completed of not
        for (UrlContentAnalyzer urlThread: urlThreads) {
            try{
                // Pauses the execution until current thread has been successfully executed
                urlThread.join();
            } catch (InterruptedException e) {
                logger.severe(e.getMessage());
            }
        }
        logger.info("*************************************************************");

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Total time taken: " + timeTaken + " milliseconds");
    }
}
