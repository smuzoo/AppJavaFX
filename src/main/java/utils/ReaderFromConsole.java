package utils;

import java.util.Scanner;

public class ReaderFromConsole extends Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String getLastLine(){
        return lastLine;
    }

    public String getNewLine(){
        lastLine = scanner.nextLine();
        return lastLine;
    }

}
