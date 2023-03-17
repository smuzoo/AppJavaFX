package commands.specific;

import collection.HumanBeingCollection;
import commands.Command;

import static colors.Colors.*;

/**
 * The command Clear collection.
 */
public class ClearCollection implements Command {

    @Override
    public void execute(String ignore){
        HumanBeingCollection.clear();
        System.out.println("Коллекция успешно очищена");
    }

    @Override
    public String description(){
        return BLUE + "clear" + RESET + " : очистить коллекцию";
    }
}
