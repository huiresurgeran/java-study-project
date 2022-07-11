package com.jsamuel.study.functional.interf.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlatMap {

    private static final Logger logger = LoggerFactory.getLogger(FlatMap.class);

    public static void main(String[] args) {
        // 多个stream连成一个stream
        List<Integer> result = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(a -> a.stream())
                .collect(Collectors.toList());
        result.forEach(i -> logger.info("integer: {}", i));

        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        flatMapList.forEach(i -> logger.info("flatMapList: {}", i));

    }
}
