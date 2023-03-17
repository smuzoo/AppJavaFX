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

/**
 * The command Remove greater human being.
 */
public class RemoveGreaterHumanBeing implements Command {
    private final Reader reader;

    /**
     * Instantiates a new Remove greater human being.
     *
     * @param reader the reader
     */
    public RemoveGreaterHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String ignore){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        HumanBeing human = creatorHumanBeingObject.create();
        Set<Map.Entry<UUID, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) < 0);
        System.out.println("Все элементы больше заданного были удалены");
    }

    @Override
    public String description(){
        return BLUE + "remove_greater" + PURPLE + " {element}" + RESET + " : удалить из коллекции все элементы, превышающие заданный";
    }
}