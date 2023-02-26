package commands;

import collection.HumanBeing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowCollection implements Command {

    @Override
    public void execute(Map<UUID, HumanBeing> humanBeingCollection){
        for(HumanBeing human : humanBeingCollection.values()){
            System.out.println(human);
        }
    }
}
