import components.WebCrawler;

public class CrawlerLauncher {
    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.start();
    }
}