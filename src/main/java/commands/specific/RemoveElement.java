package commands.specific;

import Database.Database;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.RemoveElementValidator;
import validators.fields.HumanForUserValidator;

import static colors.Colors.*;

/**
 * The command Remove element.
 */
public class RemoveElement implements Command {

    @Override
    public void execute(String argument){
        RemoveElementValidator removeElementValidator = new RemoveElementValidator(argument);
        if(removeElementValidator.isValid()){
            Long id = Long.parseLong(argument);
            HumanForUserValidator hsev = new HumanForUserValidator(HumanBeingCollection.getHuman(id));
            if(hsev.isValid()){
                Database db = Database.getInstance();
                int update = db.deleteById("human_beings", id);
                if(update > 0){
                    HumanBeingCollection.remove(id);
                    System.out.println("Элемент был успешно удалён");
                }
            }


        }
    }

    @Override
    public String description(){
        return BLUE + "remove_key" + PURPLE + " null" + RESET + " : удалить элемент из коллекции по его ключу";
    }
}
