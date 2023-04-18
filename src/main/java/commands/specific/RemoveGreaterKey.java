package commands.specific;

import Database.Database;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.RemoveGreaterKeyValidator;
import validators.fields.HumanForUserValidator;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static colors.Colors.*;

/**
 * The command Remove greater key.
 */
public class RemoveGreaterKey implements Command {

    @Override
    public void execute(String argument){
        RemoveGreaterKeyValidator removeGreaterKeyValidator = new RemoveGreaterKeyValidator(argument);
        if(removeGreaterKeyValidator.isValid()){
            Long id = Long.parseLong(argument);
            Set<Map.Entry<Long, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
            Set<Map.Entry<Long, HumanBeing>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                    humanInCollection.getKey().compareTo(id) > 0 &&
                            new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
            Database db = Database.getInstance();
            long sumUpdate = 0;

            for (Map.Entry<Long, HumanBeing> humanBeing : humanBeingFilter) {
                sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
            }if(sumUpdate > 0){
                humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getKey().compareTo(id) > 0);
                System.out.println("Элементы, с id больше заданного, были удалены");
            }else {
                System.out.println("Элементов, с id больше заданного, не существует");
            }
            db.closeConnection();

        }

        }


    @Override
    public String description(){
        return BLUE + "remove_greater_key" + PURPLE + " null" + RESET + " : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
