package com.vasus1998;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;

public class Main {

    public static String get(String HTMLfromurl) throws Exception {
        StringBuilder sb = new StringBuilder();
        for(Scanner sc = new Scanner(new URL(HTMLfromurl).openStream()); sc.hasNext(); )
            sb.append(sc.nextLine()).append('\n');
        return sb.toString();
    }
    public static void main(String[] arg) throws Exception {
        String inputURL = "https://www.simbirsoft.com/";
        String text = get(inputURL).replaceAll("[^\\x00-\\xFF]", " ");
        String taboo = "[-_\\[\\]\"\'\n\r\t|,.<>/='\";^%$#@!*()\\s\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]";
        String[] newtext = text.split(taboo);
        Map<String, Integer> podschet = new HashMap<>();
        for (String num : newtext){
            Integer count = podschet.get(num);
            if (count == null){
                count = 0;
            }
            podschet.put(num, count + 1);
        }
        try (PrintStream out = new PrintStream(new FileOutputStream("filename.txt"))) {
            out.print(podschet);
        }
    }
}
