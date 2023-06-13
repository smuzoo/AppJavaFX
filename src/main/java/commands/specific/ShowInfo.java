package commands.specific;

import collection.VehicleCollection;
import commands.Command;

/**
 * The command Show info.
 */
public class ShowInfo implements Command {

    @Override
    public void execute(String ignore){
        System.out.println("Коллекция HashMap<java.util.UUID, HumanBeing>");
        System.out.println("Дата создания коллекции: " + VehicleCollection.getDateOfInitialization());
        System.out.println("Дата последнего изменения коллекции: " + VehicleCollection.getDateOfLastChange());
        System.out.println("Количество элементов в коллекции: " + VehicleCollection.getCountVehicleCollection());
    }

    public String execute(){
        String res = "Коллекция HashMap<java.util.UUID, HumanBeing>";
        res += "\nДата создания коллекции: " + VehicleCollection.getDateOfInitialization();
        res += "\nДата последнего изменения коллекции: " + VehicleCollection.getDateOfLastChange();
        res += "\nКоличество элементов в коллекции: " + VehicleCollection.getCountVehicleCollection();
        return res;
    }

    @Override
    public String description(){
        return "info" + " :  вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
