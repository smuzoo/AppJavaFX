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
import java.util.UUID;
import java.util.stream.Collectors;

import static colors.Colors.*;

/**
 * The command Remove lower human being.
 */
public class RemoveLowerHumanBeing implements Command {

    private final Reader reader;


    /**
     * Instantiates a new Remove lower human being.
     *
     * @param reader the reader
     */
    public RemoveLowerHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String ignore){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        HumanBeing human = creatorHumanBeingObject.create();
        Set<Map.Entry<Long, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        Set<Map.Entry<Long, HumanBeing>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                humanInCollection.getValue().compareTo(human) > 0 &&
                        new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
        Database db = Database.getInstance();
        long sumUpdate = 0;

        for (Map.Entry<Long, HumanBeing> humanBeing : humanBeingFilter) {
            sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
        }if(sumUpdate > 0){
            humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) > 0);
            System.out.println("Все элементы меньше заданного были удалены");
        }else {
            System.out.println("Элементов меньше заданного не существует");
        }
        db.closeConnection();

    }

    public void remove(HumanBeing human){
        Set<Map.Entry<Long, HumanBeing>> humanBeingEntrySet = HumanBeingCollection.getEntrySet();
        Set<Map.Entry<Long, HumanBeing>> humanBeingFilter = humanBeingEntrySet.stream().filter(humanInCollection ->
                humanInCollection.getValue().compareTo(human) > 0 &&
                        new HumanForUserValidator(humanInCollection.getValue()).isValid()).collect(Collectors.toSet());
        Database db = Database.getInstance();
        long sumUpdate = 0;

        for (Map.Entry<Long, HumanBeing> humanBeing : humanBeingFilter) {
            sumUpdate += db.deleteById("human_beings", humanBeing.getKey());
        }if(sumUpdate > 0){
            humanBeingEntrySet.removeIf(humanInCollection -> humanInCollection.getValue().compareTo(human) > 0);
        }
    }


    @Override
    public String description(){
        return "remove_lower" + " {element}" + " : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
