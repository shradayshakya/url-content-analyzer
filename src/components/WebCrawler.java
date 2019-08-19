package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WebCrawler {
    public void start(){
        long startTime = System.currentTimeMillis();
        Logger logger = Logger.getLogger(WebCrawler.class.getName());

        String[] keywords = { "oracle" , "java" };
        String[] urls = {
                "https://docs.oracle.com/en/java/",
                "https://docs.oracle.com/en/database/",
                "https://docs.oracle.com/en/operating-systems/"
        };

        List<UrlContentAnalyzer> urlThreads = new ArrayList<>();

        for (String url : urls){
            UrlContentAnalyzer urlThread = new UrlContentAnalyzer(url, keywords);
            urlThread.start();
            urlThreads.add(urlThread);
        }

        logger.info("*************************************************************");
        for (UrlContentAnalyzer urlThread: urlThreads) {
            try{
                urlThread.join();

                Map<String, Integer> keywordMap = urlThread.getKeywordMap();
                for (String keyword: keywordMap.keySet()) {
                    logger.info(
                            urlThread.getUrlString() + " : '" +
                                    keyword + "' => " +
                                    keywordMap.get(keyword)
                    );

                    logger.info(urlThread.getUrlString() + "\n'");
                }

            } catch (InterruptedException e) {
                logger.severe(e.getMessage());
            }
        }
        logger.info("*************************************************************");

        long timeTaken = System.currentTimeMillis() - startTime;

        logger.info("Total time taken: " + timeTaken + " milliseconds");
    }
}
