package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.RemoveGreaterKeyValidator;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import static colors.Colors.*;

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
        return BLUE + "remove_greater_key" + PURPLE + " null" + RESET + " : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
