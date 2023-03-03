package collection;

import utils.ReaderHumanBeingCollection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HumanBeingCollection {

    private static Map<UUID, HumanBeing> humanBeingCollection;
    private static Date dateOfInitialization;
    private static Date dateOfLastChange;

    static {
        humanBeingCollection = new HashMap<>();
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public static void readFile(String FILE_PATH){
        ReaderHumanBeingCollection reader = new ReaderHumanBeingCollection(FILE_PATH);
        humanBeingCollection = reader.read();
    }

    public static void add(HumanBeing human){
        dateOfLastChange = new Date();
        humanBeingCollection.put(human.getId(), human);
    }

    public static void clear(){
        dateOfLastChange = new Date();
        humanBeingCollection.clear();
    }

    public static void remove(UUID id){
        humanBeingCollection.remove(id);
    }

    public static long getCountHumanBeingCollection(){
        return humanBeingCollection.size();
    }

    public static Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    public static Map<UUID, HumanBeing> getHumanBeingCollection() {
        return humanBeingCollection;
    }

    public static Date getDateOfInitialization() {
        return dateOfInitialization;
    }
}
