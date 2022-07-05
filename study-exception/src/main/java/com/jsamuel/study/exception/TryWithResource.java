package com.jsamuel.study.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TryWithResource {

    public static void example(String filePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(filePath), "UTF-8")) {

        } catch (IOException e) {

        }
    }

    public static void automaticallyCloseResource(String filePath) {
        File file = new File("");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            
        }

    }
}
