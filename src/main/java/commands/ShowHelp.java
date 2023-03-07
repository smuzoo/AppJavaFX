package commands;

import java.util.Map;

public class ShowHelp implements Command {

    @Override
    public void execute(String ignore) {
        CommandController commandController = new CommandController();
        Map<String, Command> commands = commandController.getCommands();
        for(Command command : commands.values()){
            System.out.println(command.description());
        }
    }

    @Override
    public String description(){
        return "help : вывести справку по доступным командам";
    }
}