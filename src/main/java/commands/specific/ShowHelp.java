package commands.specific;

import commands.Command;
import commands.CommandController;
import utils.readers.ReaderFromConsole;

import java.util.Map;

import static colors.Colors.*;

/**
 * The command Show help.
 */
public class ShowHelp implements Command {

    @Override
    public void execute(String ignore) {
        CommandController commandController = new CommandController(new ReaderFromConsole());
        Map<String, Command> commands = commandController.getCommands();
        commands.values().forEach(command -> System.out.println(command.description()));
    }

    public String execute() {
        CommandController commandController = new CommandController(new ReaderFromConsole());
        Map<String, Command> commands = commandController.getCommands();
        StringBuilder stringBuilder = new StringBuilder();

        commands.values().forEach(command -> stringBuilder.append(command.description()).append("\n"));

        return stringBuilder.toString();
    }

    @Override
    public String description(){
        return "help" + " : вывести справку по доступным командам";
    }
}