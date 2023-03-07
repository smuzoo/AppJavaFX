package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<String, Command> commands = new HashMap<>();

    {
        addCommand("show", new ShowCollection());
        addCommand("help", new ShowHelp());
        addCommand("info", new ShowInfo());
        addCommand("clear", new ClearCollection());
        addCommand("remove_key", new RemoveElement());
        addCommand("remove_greater_key", new RemoveGreaterKey());
        addCommand("count_greater_than_impact_speed", new CountGreaterThanImpactSpeed());
        addCommand("filter_less_than_impact_speed", new ShowLessThanImpactSpeed());
        addCommand("print_field_descending_mood", new ShowFieldDescendingMood());

    }

    public void executeCommand(String commandText){
        final String[] commandWithArgument = commandText.split(" ");
        final String commandName = commandWithArgument[0];
        final String argument = commandWithArgument.length > 1 ?  commandWithArgument[1] : "";
        Command command = getCommand(commandName);
        if(command == null) System.out.println("У меня нет такой команды");
        else{
            command.execute(argument);
        }
    }

    public void addCommand(String nameCommand, Command command){
        commands.put(nameCommand, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
