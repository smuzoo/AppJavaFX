package commands;

import utils.ReaderFromConsole;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<String, Command> commands = new HashMap<>();


    {
        commands.put("show", new ShowCollection());
        commands.put("help", new ShowHelp());
        commands.put("info", new ShowInfo());
        commands.put("clear", new ClearCollection());
        commands.put("remove_key", new RemoveElement());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("count_greater_than_impact_speed", new commands.CountGreaterThanImpactSpeed());
    }


    public void executeCommand(String commandText){
        Command command = commands.get(commandText);
        if(command == null) System.out.println("У меня нет такой команды");
        else{
            command.execute(ReaderFromConsole.getLastLine().split(" "));
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
