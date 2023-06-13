package commands.specific;

import Database.Database;
import collection.VehicleCollection;
import commands.Command;

/**
 * The command Clear collection.
 */
public class ClearCollection implements Command {

    @Override
    public void execute(String ignore){
        Database db = Database.getInstance();
        db.truncateTable("human_beings", "user_id_seq");
        VehicleCollection.clear();
        System.out.println("Коллекция успешно очищена");
    }


    @Override
    public String description(){
        return "clear" + " : очистить коллекцию";
    }
}
