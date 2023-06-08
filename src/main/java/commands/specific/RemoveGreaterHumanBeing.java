package commands.specific;

import Database.Database;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.readers.Reader;
import validators.fields.HumanForUserValidator;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Map.Entry<Long, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        Set<Map.Entry<Long, HumanBeing>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                humanInCollection.getValue().compareTo(human) < 0 &&
                        new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
        Database db = Database.getInstance();
        long sumUpdate = 0;

            for (Map.Entry<Long, HumanBeing> humanBeing : humanBeingFilter) {
                sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
        }if(sumUpdate > 0){
            humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) < 0);
            System.out.println("Все элементы больше заданного были удалены");
        }else {
            System.out.println("Элементов больше заданного не существует");
        }

    }

    public void remove(HumanBeing human){
        Set<Map.Entry<Long, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        Set<Map.Entry<Long, HumanBeing>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                humanInCollection.getValue().compareTo(human) < 0 &&
                        new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
        Database db = Database.getInstance();
        long sumUpdate = 0;

        for (Map.Entry<Long, HumanBeing> humanBeing : humanBeingFilter) {
            sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
        }if(sumUpdate > 0){
            humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) < 0);
        }
    }

    @Override
    public String description(){
        return "remove_greater" + " {element}" + " : удалить из коллекции все элементы, превышающие заданный";
    }
}