package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.Reader;

public class RemoveGreaterHumanBeing implements Command {
    private final Reader reader;

    public RemoveGreaterHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String ignore){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        HumanBeing human = creatorHumanBeingObject.create();
        for(HumanBeing humanInCollection : HumanBeingCollection.getHumanBeings()){
            if(humanInCollection.compareTo(human) > 0){
                HumanBeingCollection.remove(humanInCollection.getId());
            }
        }
        System.out.println("Все элементы больше заданного были удалены");
    }

    @Override
    public String description(){
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}