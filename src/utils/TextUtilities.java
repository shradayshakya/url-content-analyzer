package utils;

public class TextUtilities {
    public static int countOccurrence(String text, String keyword) {
        int count = 0;
        for (String chuck: text.split(" ")){
            if(chuck.contains(keyword)) {
                count++;
            }
        }
        return count;
    }

    public static String parseHTML(String text){
        return text.replaceAll("(?i)<td[^>]*>", "");
    }
}
