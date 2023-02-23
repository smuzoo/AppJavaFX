package commands;

import collection.HumanBeing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowCollection {
    private Map<UUID, HumanBeing> humanBeingCollection;
    public ShowCollection(Map<UUID, HumanBeing> humanBeingCollection){
        this.humanBeingCollection = humanBeingCollection;
    }
    public void show(){
        for(HumanBeing human : humanBeingCollection.values()){
            System.out.println(human);
        }
    }
}
