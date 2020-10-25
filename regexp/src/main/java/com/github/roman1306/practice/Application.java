package com.github.roman1306.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args) {
        String text = "Notification has been sent 150 times to +4(351) 455 22 44 successfully. +1(431) 542 56 12 is unreachable";

        System.out.println(handlerText(text));
    }

    private static String handlerText (String text) {

        Pattern handler = Pattern.compile("\\+\\d\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}");
        Matcher matcher = handler.matcher(text);

        while (matcher.find()) {
            text = text.replace(matcher.group(), matcher.group().replaceAll("\\+|\\s+|\\(|\\)" ,""));
        }

        return text;
    }

}
