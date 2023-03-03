package commands;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import java.util.Map;
import java.util.UUID;

public class ShowCollection implements Command {

    @Override
    public void execute(){
        Map<UUID, HumanBeing> humanBeingCollection = HumanBeingCollection.getHumanBeingCollection();
        for(HumanBeing human : humanBeingCollection.values()){
            System.out.println(human);
        }
    }

    @Override
    public String description(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
