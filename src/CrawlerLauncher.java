import components.WebCrawler;

public class CrawlerLauncher {
    public static void main(String[] args) {
        // Application Entry point
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.start();
    }
}