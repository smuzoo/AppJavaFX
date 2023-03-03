package commands;

import collection.HumanBeingCollection;

public class ShowInfo implements Command{

    @Override
    public void execute(){
        System.out.println("Коллекция HashMap<java.util.UUID, HumanBeing>");
        System.out.println("Дата создания коллекции: " + HumanBeingCollection.getDateOfInitialization());
        System.out.println("Дата последнего изменения коллекции: " + HumanBeingCollection.getDateOfLastChange());
        System.out.println("Количество элементов в коллекции: " + HumanBeingCollection.getCountHumanBeingCollection());
    }
}
