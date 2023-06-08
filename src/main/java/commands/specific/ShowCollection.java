package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;

import java.util.Map;
import java.util.UUID;
import static colors.Colors.*;

/**
 * The command Show collection.
 */
public class ShowCollection implements Command {

    @Override
    public void execute(String ignore){
        HumanBeingCollection.getHumanBeings().forEach(System.out::println);
    }

    @Override
    public String description(){
        return "show" + " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
