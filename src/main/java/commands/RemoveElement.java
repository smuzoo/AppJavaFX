package commands;

import collection.HumanBeingCollection;
import utils.ReaderFromConsole;
import validators.commands.RemoveElementValidator;

import java.util.UUID;

public class RemoveElement implements Command{

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
        return "remove_key null : удалить элемент из коллекции по его ключу";
    }
}
