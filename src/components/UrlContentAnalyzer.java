package components;

import utils.ContentExtractor;
import utils.TextUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UrlContentAnalyzer extends Thread {
    private String urlString;
    private static Map<String, Integer> keywordMap;

    public void run(){
        long startTime = System.currentTimeMillis();

        Logger logger = Logger.getLogger(WebCrawler.class.getName());
        String content = TextUtilities.parseHTML(ContentExtractor.getContentFromUrl(urlString));

        for (String keyword : keywordMap.keySet()) {
            int count = TextUtilities.countOccurrence(content, keyword);

            long timeTaken = System.currentTimeMillis() - startTime;
            logger.info(
                    this.urlString + " : '" +
                            keyword + "' => " +
                            count + " occurrence ("
                            + timeTaken + " ms)"
            );
            synchronized ( UrlContentAnalyzer.keywordMap ) {
                UrlContentAnalyzer.keywordMap.replace(keyword,UrlContentAnalyzer.keywordMap.get(keyword) + count );
                logger.info(
                        "Total : '" +
                                keyword + "' => " +
                                UrlContentAnalyzer.keywordMap.get(keyword) + " occurrence ("
                                + timeTaken + " ms)"
                );
            }
        }
    }

    public static void initiateKeywordMap(String[] keywords) {
        UrlContentAnalyzer.keywordMap = new HashMap<>();
        for (String keyword: keywords) {
            UrlContentAnalyzer.keywordMap.put(keyword, 0);
        }
    }

    public UrlContentAnalyzer(String urlString){
        this.urlString = urlString;
    }
}
