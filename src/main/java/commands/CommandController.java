package commands;

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
    }


    public void executeCommand(String commandText){
        Command command = commands.get(commandText);
        if(command == null) System.out.println("У меня нет такой команды");
        else{
            command.execute();
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
