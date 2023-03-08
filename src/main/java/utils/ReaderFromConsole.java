package utils;

import java.util.Scanner;

public class ReaderFromConsole extends Reader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getLastLine(){
        return lastLine;
    }

    @Override
    public String getNewLine(){
        lastLine = scanner.nextLine();
        return lastLine;
    }

}
