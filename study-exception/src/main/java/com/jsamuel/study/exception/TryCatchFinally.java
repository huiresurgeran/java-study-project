package com.jsamuel.study.exception;

import com.jsamuel.study.exception.customize.MyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryCatchFinally {

    private static final Logger logger = LoggerFactory.getLogger(TryCatchFinally.class);

    public static void example() {
        try {
            // 执行程序代码，可能会出现异常
        } catch (Exception e) {
            // 捕获异常并处理
        } finally {
            // 必执行的代码
        }
    }

    private static void readFile(String filePath) throws MyException {
        logger.info("readFile start!");

        File file = new File(filePath);
        String result;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((result = reader.readLine()) != null) {
                logger.info(result);
            }
        } catch (FileNotFoundException e) {
            logger.error("readFile failed, ", e);
            MyException ex = new MyException("readFile failed");
            ex.initCause(e);
            throw ex;
        } catch (IOException e) {
            logger.error("readFile failed, ", e);
            MyException ex = new MyException("readFile failed");
            ex.initCause(e);
            throw ex;
        } finally {
            logger.info("readFile end!");
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("close reader failed, ", e);
                }
            }
        }
    }
}
