package components;

import utils.ContentExtractor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class WebCrawler {

    static final int POOL_SIZE = 5;

    public void start(){
        long startTime = System.currentTimeMillis();

        Logger logger = Logger.getLogger(WebCrawler.class.getName());
        String[] keywords = { "oracle", "java"};
        List<String> urls = ContentExtractor.getListFromFile("src/urls.txt");

        ExecutorService analyzersManager = Executors.newFixedThreadPool(POOL_SIZE);

        logger.info("*************************************************************");
        for (String url : urls){
            UrlContentAnalyzer analyzer = new UrlContentAnalyzer(url, keywords);
            analyzersManager.execute(analyzer);
        }
        analyzersManager.shutdown();
        // Waiting to complete execution of all threads
        while (!analyzersManager.isTerminated()) { }
        logger.info("*************************************************************");

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Total time taken: " + timeTaken + " milliseconds");
    }
}
