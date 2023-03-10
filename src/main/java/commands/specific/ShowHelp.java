package commands.specific;

import commands.Command;
import commands.CommandController;
import utils.readers.ReaderFromConsole;

import java.util.Map;

import static colors.Colors.*;

public class ShowHelp implements Command {

    @Override
    public void execute(String ignore) {
        CommandController commandController = new CommandController(new ReaderFromConsole());
        Map<String, Command> commands = commandController.getCommands();
        for(Command command : commands.values()){
            System.out.println(command.description());
        }
    }

    @Override
    public String description(){
        return BLUE + "help" + RESET + " : вывести справку по доступным командам";
    }
}