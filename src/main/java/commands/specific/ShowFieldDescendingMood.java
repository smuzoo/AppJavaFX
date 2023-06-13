package commands.specific;

import collection.Vehicle;
import collection.VehicleCollection;
import commands.Command;

import java.util.*;

/**
 * The command Show field descending mood.
 */
public class ShowFieldDescendingMood implements Command {

    @Override
    public void execute(String ignore){
        List<Vehicle> vehicleSortedByMood = new ArrayList<>(VehicleCollection.getVehicles());
        vehicleSortedByMood.sort((human1, human2) -> {
            if (human1.getFuelType() == null & human2.getFuelType() != null) return 1;
            else if (human1.getFuelType() != null & human2.getFuelType() == null) return -1;
            else if (human1.getFuelType() == null & human2.getFuelType() == null) return 0;
            return human2.getFuelTypePower() - human1.getFuelTypePower();
        });
        vehicleSortedByMood.forEach(human -> { System.out.println(human.getFuelType()); });
    }

    @Override
    public String description(){
        return "print_field_descending_mood" + " : вывести значения поля mood всех элементов в порядке убывания";
    }
}
