package com.jsamuel.study.exception;

public class Application {

    public static void main(String[] args) {
        try {
            long l1 = 1234567890123456789L;
            long l2 = 1L;
            int i1 = (int) (l1 - l2);
            System.out.print(i1);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }


    }
}
