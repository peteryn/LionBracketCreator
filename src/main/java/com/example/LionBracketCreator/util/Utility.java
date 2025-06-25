package com.example.LionBracketCreator.util;

import java.util.stream.Collectors;

public class Utility {
    public static String removeNonAlphabeticUsingStreams(String input) {
        return input.chars()
                .filter(c -> {
                    return Character.isLetter(c) || Character.isDigit(c);
                })
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }
}
