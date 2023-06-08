package commands.specific;

import collection.HumanBeingCollection;
import commands.Command;
import static colors.Colors.*;

/**
 * The command Show info.
 */
public class ShowInfo implements Command {

    @Override
    public void execute(String ignore){
        System.out.println("Коллекция HashMap<java.util.UUID, HumanBeing>");
        System.out.println("Дата создания коллекции: " + HumanBeingCollection.getDateOfInitialization());
        System.out.println("Дата последнего изменения коллекции: " + HumanBeingCollection.getDateOfLastChange());
        System.out.println("Количество элементов в коллекции: " + HumanBeingCollection.getCountHumanBeingCollection());
    }

    public String execute(){
        String res = "Коллекция HashMap<java.util.UUID, HumanBeing>";
        res += "\nДата создания коллекции: " + HumanBeingCollection.getDateOfInitialization();
        res += "\nДата последнего изменения коллекции: " + HumanBeingCollection.getDateOfLastChange();
        res += "\nКоличество элементов в коллекции: " + HumanBeingCollection.getCountHumanBeingCollection();
        return res;
    }

    @Override
    public String description(){
        return "info" + " :  вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
