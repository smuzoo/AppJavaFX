package commands.specific;

import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.RemoveElementValidator;

import java.util.UUID;

import static colors.Colors.*;

/**
 * The command Remove element.
 */
public class RemoveElement implements Command {

    @Override
    public void execute(String argument){
        RemoveElementValidator removeElementValidator = new RemoveElementValidator(argument);
        if(removeElementValidator.isValid()){
            HumanBeingCollection.remove(UUID.fromString(argument));
            System.out.println("Элемент был успешно удалён");
        }
    }

    @Override
    public String description(){
        return BLUE + "remove_key" + PURPLE + " null" + RESET + " : удалить элемент из коллекции по его ключу";
    }
}
