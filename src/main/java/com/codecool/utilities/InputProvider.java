package com.codecool.utilities;

import java.util.Scanner;

public class InputProvider {

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String prompt) {
        System.out.print(prompt);
        try {
            String msg = "Please, enter a positive number:";
            int n;
            do {
                System.out.println(msg);
                // make the user keep entering the input until it's an Integer
                while (!sc.hasNextInt()) {
                    System.out.println(msg);
                    sc.next();
                }
                n = sc.nextInt();
            } while (n <= 0); // run until the input is a positive Integer
            return n;
        } finally {}
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return sc.next();
    }
}