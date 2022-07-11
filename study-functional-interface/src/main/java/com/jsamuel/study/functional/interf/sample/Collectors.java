package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Collectors {
    private static final Logger logger = LoggerFactory.getLogger(Collectors.class);

    public static void main(String[] args) {
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream()
                .map(x -> x.toUpperCase())
                .collect(java.util.stream.Collectors.joining(", "));
        logger.info("g7 countries: {}", G7Countries);
    }
}
