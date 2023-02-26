package commands;

import collection.HumanBeing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandController {
    private Map<UUID, HumanBeing> humanBeingCollection;
    private Map<String, Command> commands = new HashMap<>();

    public CommandController(Map<UUID, HumanBeing> humanBeingCollection){
        this.humanBeingCollection = humanBeingCollection;
    }

    public void setCommand(String commandText, Command command){
        commands.put(commandText, command);
    }

    public void executeCommand(String commandText){
        Command command = commands.get(commandText);
        if(command == null) System.out.println("У меня нет такой команды");
        else{
            command.execute(humanBeingCollection);
        }
    }

    public void init(){
        ShowCollection showCollection = new ShowCollection();
        setCommand("show", showCollection);
    }
}
