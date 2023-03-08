package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.RemoveGreaterKeyValidator;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class RemoveGreaterKey implements Command {

    @Override
    public void execute(String argument){
        RemoveGreaterKeyValidator removeGreaterKeyValidator = new RemoveGreaterKeyValidator(argument);
        if(removeGreaterKeyValidator.isValid()){
            UUID id = UUID.fromString(argument);
            Set<Map.Entry<UUID, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
            humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getKey().compareTo(id) > 0);
            System.out.println("Элементы, с id больше заданного были удалены");
        }
    }

    @Override
    public String description(){
        return "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
