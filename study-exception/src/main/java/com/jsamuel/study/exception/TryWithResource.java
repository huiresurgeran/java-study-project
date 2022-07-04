package com.jsamuel.study.exception;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TryWithResource {

    public static void example(String filePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(filePath), "UTF-8")) {

        } catch (IOException e) {

        }
    }
}
