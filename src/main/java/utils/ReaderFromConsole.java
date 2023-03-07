package utils;

import java.util.Scanner;

public class ReaderFromConsole {
    private final Scanner scanner = new Scanner(System.in);
    private String lastLine;

    public String getLastLine(){
        return lastLine;
    }

    public String getNewLine(){
        lastLine = scanner.nextLine();
        return lastLine;
    }

}
