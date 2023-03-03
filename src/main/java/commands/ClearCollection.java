package commands;

import collection.HumanBeingCollection;

public class ClearCollection implements Command{

    @Override
    public void execute(){
        HumanBeingCollection.clear();
        System.out.println("Коллекция успешно очищена");
    }
}
