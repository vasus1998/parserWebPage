package com.vasus1998;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;

public class Main {

    private final static String CHARACTERS_SPLIT = "[-_\\[\\]\"'\n\r\t|,.<>/='\";^%$#@!*()\\s\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]";
    private final static String DEFAULT_URL = "https://www.simbirsoft.com/";
    private final static String RESULT_FILE_NAME = "filename.txt";

    public static String getTextFromWebPage(String url) throws Exception {
        StringBuilder result = new StringBuilder();
        for(Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext(); )
            result.append(sc.nextLine()).append(System.lineSeparator());
        return result.toString();
    }
    public static void main(String[] arg) throws Exception {
        String inputURL;
        if(arg.length > 0 && !arg[0].isEmpty()) {
            inputURL = arg[0];
        } else {
            inputURL = DEFAULT_URL;
        }

        String textFromWebPage = getTextFromWebPage(inputURL);
        String[] wordsFromWebPage = textFromWebPage.split(CHARACTERS_SPLIT);
        Map<String, Integer> countOfWords = new HashMap<>();
        for (String wordFromWebPage: wordsFromWebPage){
            Integer countWordFromWebPage = countOfWords.get(wordFromWebPage);
            if (countWordFromWebPage == null){
                countWordFromWebPage = 0;
            }
            countOfWords.put(wordFromWebPage, countWordFromWebPage + 1);
        }
        try (PrintStream out = new PrintStream(new FileOutputStream(RESULT_FILE_NAME))) {
            out.print(countOfWords);
        }
    }
}
