package com.test.playwright;

import java.util.Arrays;
import java.util.List;

public class CustomStringUtils {
    public static List<String> splitString(String text, String separator) {
        return Arrays.asList(text.split(separator));
    }
}
