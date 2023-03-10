package commands.specific;

import commands.Command;

import static colors.Colors.BLUE;
import static colors.Colors.RESET;

public class Exit implements Command {

    @Override
    public void execute(String ignore){
        System.exit(0);
    }

    @Override
    public String description(){
        return BLUE + "exit" + RESET + ": завершить программу (без сохранения в файл)";
    }
}
