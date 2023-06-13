package commands.specific;

import collection.VehicleCollection;
import commands.Command;

/**
 * The command Show collection.
 */
public class ShowCollection implements Command {

    @Override
    public void execute(String ignore){
        VehicleCollection.getVehicles().forEach(System.out::println);
    }

    @Override
    public String description(){
        return "show" + " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
