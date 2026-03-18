package com.solvd.deliverycompany.utilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class WordCounter {

    private static final Logger logger = LogManager.getLogger(WordCounter.class);

    public static void countWords(String inputPath, String outputPath) {

        try {
            String text = FileUtils.readFileToString(new File(inputPath), StandardCharsets.UTF_8).replaceAll("[^a-zA-Zа-яА-Я0-9]", " ");

            Map<String, Long> wordCount = Arrays.stream(StringUtils.split(text))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            String result = wordCount.entrySet().stream()
                    .map(e -> e.getKey() + " - " + e.getValue())
                    .collect(Collectors.joining("\n"));

            FileUtils.writeStringToFile(new File(outputPath), result, StandardCharsets.UTF_8);

        } catch (Exception e) {
            logger.error("Error!");
            e.printStackTrace();
        }
    }
}
