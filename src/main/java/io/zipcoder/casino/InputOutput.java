package io.zipcoder.casino;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutput {

    private final Scanner input;
    private final PrintStream output;

    public InputOutput(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    public void println(String prompt, Object... args) {
        output.format(prompt + "\n", args);
    }

    public String getInputString(String prompt, Object... args) {
        println(prompt, args);
        return input.nextLine();
    }

    public Double getInputDouble(String prompt, Object... args) {
        String stringInput = getInputString(prompt, args);
        do {
            try {
                Double doubleInput = Double.parseDouble(stringInput);
                return doubleInput;
            } catch (Exception e) {
                println("%s is invalid. Please enter a number here", stringInput);
            }
        } while (true);
    }

    public Integer getInputInteger(String prompt, Object... args) {
        return getInputDouble(prompt, args).intValue();
    }


    public static String getStringInput(String prompt, Object... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextInt();
    }
}