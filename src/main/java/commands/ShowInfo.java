package commands;

import collection.HumanBeingCollection;

public class ShowInfo implements Command{

    @Override
    public void execute(String ignore){
        System.out.println("Коллекция HashMap<java.util.UUID, HumanBeing>");
        System.out.println("Дата создания коллекции: " + HumanBeingCollection.getDateOfInitialization());
        System.out.println("Дата последнего изменения коллекции: " + HumanBeingCollection.getDateOfLastChange());
        System.out.println("Количество элементов в коллекции: " + HumanBeingCollection.getCountHumanBeingCollection());
    }

    @Override
    public String description(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
