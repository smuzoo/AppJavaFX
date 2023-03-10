package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.readers.Reader;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import static colors.Colors.*;

public class RemoveLowerHumanBeing implements Command {

    private final Reader reader;

    public RemoveLowerHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String ignore){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        HumanBeing human = creatorHumanBeingObject.create();
        Set<Map.Entry<UUID, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) < 0);
        System.out.println("Все элементы меньше заданного были удалены");
    }

    @Override
    public String description(){
        return BLUE + "remove_lower" + PURPLE + " {element}" + RESET + " : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
