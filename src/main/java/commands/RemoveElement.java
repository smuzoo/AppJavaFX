package commands;

import collection.HumanBeingCollection;
import utils.ReaderFromConsole;
import validators.commands.RemoveElementValidator;

import java.util.UUID;

public class RemoveElement implements Command{

    @Override
    public void execute(){
        String[] arguments = ReaderFromConsole.getLastLine().split(" ");
        RemoveElementValidator removeElementValidator = new RemoveElementValidator(arguments);
        if(removeElementValidator.isValid()){
            HumanBeingCollection.remove(UUID.fromString(arguments[1]));
            System.out.println("Элемент был успешно удалён");
        }
    }

    @Override
    public String description(){
        return "remove_key null : удалить элемент из коллекции по его ключу";
    }
}
