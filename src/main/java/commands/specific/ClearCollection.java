package commands.specific;

import collection.HumanBeingCollection;
import commands.Command;

public class ClearCollection implements Command {

    @Override
    public void execute(String ignore){
        HumanBeingCollection.clear();
        System.out.println("Коллекция успешно очищена");
    }

    @Override
    public String description(){
        return "clear : очистить коллекцию";
    }
}
