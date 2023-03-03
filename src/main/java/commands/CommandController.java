package commands;

import collection.HumanBeing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandController {
    private Map<String, Command> commands = new HashMap<>();


    public void setCommand(String commandText, Command command){
        commands.put(commandText, command);
    }

    public void executeCommand(String commandText){
        Command command = commands.get(commandText);
        if(command == null) System.out.println("У меня нет такой команды");
        else{
            command.execute();
        }
    }

    public void init(){
        setCommand("show", new ShowCollection());
        setCommand("help", new ShowHelp());
        setCommand("info", new ShowInfo());
        setCommand("clear", new ClearCollection());
    }
}
