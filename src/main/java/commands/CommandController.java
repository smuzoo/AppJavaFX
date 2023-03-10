package commands;

import commands.specific.*;
import utils.readers.Reader;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<String, Command> commands = new HashMap<>();
    private final Reader reader;

    public CommandController(Reader reader) {
        this.reader = reader;
        init();
    }

    public void executeCommand(String commandText) {
        final String[] commandWithArgument = commandText.split(" ");
        final String commandName = commandWithArgument.length > 0 ? commandWithArgument[0] : "";
        final String argument = commandWithArgument.length > 1 ? commandWithArgument[1] : "";
        Command command = getCommand(commandName);
        if (command == null) System.out.println("У меня нет такой команды");
        else {
            command.execute(argument);
        }
    }

    private void init() {
        addCommand("show", new ShowCollection());
        addCommand("help", new ShowHelp());
        addCommand("info", new ShowInfo());
        addCommand("clear", new ClearCollection());
        addCommand("remove_key", new RemoveElement());
        addCommand("remove_greater_key", new RemoveGreaterKey());
        addCommand("count_greater_than_impact_speed", new CountGreaterThanImpactSpeed());
        addCommand("filter_less_than_impact_speed", new ShowLessThanImpactSpeed());
        addCommand("print_field_descending_mood", new ShowFieldDescendingMood());
        addCommand("insert", new InsertHumanBeing(reader));
        addCommand("remove_greater", new RemoveGreaterHumanBeing(reader));
        addCommand("remove_lower", new RemoveLowerHumanBeing(reader));
        addCommand("update", new UpdateHumanBeing(reader));
        addCommand("save", new SaveCollection());
        addCommand("execute_script", new ExecuteScript());
        addCommand("exit", new Exit());

    }

    private void addCommand(String nameCommand, Command command) {
        commands.put(nameCommand, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
