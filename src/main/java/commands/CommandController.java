package commands;

import authentication.Authentication;
import authentication.User;
import commands.specific.*;
import utils.readers.Reader;
import validators.fields.NotEqualsValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Command controller.
 */
public class CommandController {
    private final Map<String, Command> commands = new HashMap<>();
    private final Reader reader;
    private List<String> historyFiles;


    /**
     * Instantiates a new Command controller.
     *
     * @param reader the reader
     */
    public CommandController(Reader reader) {
        this.reader = reader;
        init();
    }

    /**
     * Execute command.
     *
     * @param commandText the command text
     */
    public void executeCommand(String commandText) {
        final String[] commandWithArgument = commandText.split(" ");
        final String commandName = commandWithArgument.length > 0 ? commandWithArgument[0] : "";
        final String argument = commandWithArgument.length > 1 ? commandWithArgument[1] : "";
        Command command = getCommand(commandName);
        if (command == null) System.out.println("Неверная команда. Воспользуйтесь командой help " +
                "для просмотра существующих команд");
        else {
            command.execute(argument);
        }
    }

    private void init() {
        /** Initialize all commands */
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
        addCommand("execute_script", new ExecuteScript());
        addCommand("exit", new Exit());

    }

    private void addCommand(String nameCommand, Command command) {
        commands.put(nameCommand, command);
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
