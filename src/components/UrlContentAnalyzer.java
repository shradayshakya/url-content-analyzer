package components;

import utils.ContentExtractor;
import utils.TextUtilities;

import java.util.HashMap;
import java.util.Map;

public class UrlContentAnalyzer extends Thread {
    private String urlString;
    private Map<String, Integer> keywordMap;

    public void run(){
        String content = TextUtilities.parseHTML(ContentExtractor.getContent(urlString));
        for (String keyword : keywordMap.keySet()) {
            this.keywordMap.replace(keyword, TextUtilities.countOccurrence(content, keyword));
        }

    }

    public UrlContentAnalyzer(String urlString, String[] keywords){
        this.urlString = urlString;
        this.keywordMap = new HashMap<>();

        for (String keyword: keywords) {
            this.keywordMap.put(keyword, 0);
        }
    }

    public String getUrlString() {
        return urlString;
    }

    public Map<String, Integer> getKeywordMap() {
        return keywordMap;
    }
}
