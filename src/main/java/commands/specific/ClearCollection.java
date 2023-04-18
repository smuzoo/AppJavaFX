package commands.specific;

import Database.Database;
import collection.HumanBeingCollection;
import commands.Command;

import static colors.Colors.*;

/**
 * The command Clear collection.
 */
public class ClearCollection implements Command {

    @Override
    public void execute(String ignore){
        Database db = Database.getInstance();
        int update = db.truncateTable("human_beings");
        if(update > 0){
            HumanBeingCollection.clear();
            System.out.println("Коллекция успешно очищена");
        }else {
            System.out.println("Коллекция пуста");
        }
        db.closeConnection();
    }

    @Override
    public String description(){
        return BLUE + "clear" + RESET + " : очистить коллекцию";
    }
}
