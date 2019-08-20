package components;

import utils.ContentExtractor;
import utils.TextUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UrlContentAnalyzer extends Thread {
    private String urlString;
    private Map<String, Integer> keywordMap;

    public void run(){
        Logger logger = Logger.getLogger(WebCrawler.class.getName());
        String content = TextUtilities.parseHTML(ContentExtractor.getContentFromUrl(urlString));
        for (String keyword : keywordMap.keySet()) {
            this.keywordMap.replace(keyword, TextUtilities.countOccurrence(content, keyword));
        }

        for (String keyword : keywordMap.keySet()) {
            logger.info(
                    this.urlString + " : '" +
                    keyword + "' => " +
                    this.keywordMap.get(keyword)
            );
        }
    }

    public UrlContentAnalyzer(String urlString, String[] keywords){
        this.urlString = urlString;
        this.keywordMap = new HashMap<>();

        for (String keyword: keywords) {
            this.keywordMap.put(keyword, 0);
        }
    }
}
