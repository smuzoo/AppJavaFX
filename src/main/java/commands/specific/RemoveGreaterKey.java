package commands.specific;

import Database.Database;
import collection.Vehicle;
import collection.VehicleCollection;
import commands.Command;
import validators.Errors;
import validators.commands.RemoveGreaterKeyValidator;
import validators.fields.HumanForUserValidator;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The command Remove greater key.
 */
public class RemoveGreaterKey implements Command {

    @Override
    public void execute(String argument){
        RemoveGreaterKeyValidator removeGreaterKeyValidator = new RemoveGreaterKeyValidator(argument);
        if(removeGreaterKeyValidator.isValid()){
            Long id = Long.parseLong(argument);
            Set<Map.Entry<Long, Vehicle>> humanBeingEntrySet = VehicleCollection.getEntrySet();
            Set<Map.Entry<Long, Vehicle>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                    humanInCollection.getKey().compareTo(id) > 0 &&
                            new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
            Database db = Database.getInstance();
            long sumUpdate = 0;

            for (Map.Entry<Long, Vehicle> humanBeing : humanBeingFilter) {
                sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
            }if(sumUpdate > 0){
                humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getKey().compareTo(id) > 0);
                System.out.println("Элементы, с id больше заданного, были удалены");
            }else {
                System.out.println("Элементов, с id больше заданного, не существует");
            }

        }

        }

        public Errors isExecute(String argument){
            RemoveGreaterKeyValidator removeGreaterKeyValidator = new RemoveGreaterKeyValidator(argument);
            Errors error = removeGreaterKeyValidator.validateAll();
            if(error == Errors.NOTHAVEERRORS){
                Long id = Long.parseLong(argument);
                Set<Map.Entry<Long, Vehicle>> humanBeingEntrySet = VehicleCollection.getEntrySet();
                Set<Map.Entry<Long, Vehicle>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                        humanInCollection.getKey().compareTo(id) > 0 &&
                                new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
                Database db = Database.getInstance();
                long sumUpdate = 0;

                for (Map.Entry<Long, Vehicle> humanBeing : humanBeingFilter) {
                    sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
                }if(sumUpdate > 0){
                    humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getKey().compareTo(id) > 0);
                }
            }
            return error;
        }


    @Override
    public String description(){
        return "remove_greater_key" + " null" + " : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
