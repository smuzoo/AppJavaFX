package utils;

import java.util.Scanner;

public class ReaderFromConsole {
    private static final Scanner scanner = new Scanner(System.in);
    private static String lastLine;

    public static String getLastLine(){
        return lastLine;
    }

    public static String getNewLine(){
        lastLine = scanner.nextLine();
        return lastLine;
    }

}
