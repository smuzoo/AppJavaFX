package commands;

import collection.HumanBeingCollection;
import utils.ReaderFromConsole;
import validators.commands.RemoveGreaterKeyValidator;

import java.util.UUID;

public class RemoveGreaterKey implements Command{

    @Override
    public void execute(String argument){
        RemoveGreaterKeyValidator removeGreaterKeyValidator = new RemoveGreaterKeyValidator(argument);
        if(removeGreaterKeyValidator.isValid()){
            UUID id = UUID.fromString(argument);
            for(UUID humanId : HumanBeingCollection.getUUIDs()){
                if(humanId.compareTo(id) > 0) HumanBeingCollection.remove(humanId);
            }
            System.out.println("Элементы, с id больше заданного были удалены");
        }
    }

    @Override
    public String description(){
        return "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
