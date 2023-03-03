package collection;

import utils.ReaderFromFileToCollection;

import java.util.*;

public class HumanBeingCollection {

    private static Map<UUID, HumanBeing> humanBeingCollection;
    private static List<HumanBeing> humanBeingList;
    private final static Date dateOfInitialization;
    private static Date dateOfLastChange;

    static {
        humanBeingCollection = new HashMap<>();
        humanBeingList = new ArrayList<>();
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public static void readFile(String FILE_PATH){
        ReaderFromFileToCollection reader = new ReaderFromFileToCollection(FILE_PATH);
        reader.read();
        humanBeingCollection = reader.getHumanBeingCollection();
        humanBeingList = reader.getHumanBeingList();
    }

    public static void add(HumanBeing human){
        dateOfLastChange = new Date();
        humanBeingCollection.put(human.getId(), human);
        humanBeingList.add(human);
    }

    public static void clear(){
        dateOfLastChange = new Date();
        humanBeingCollection.clear();
        humanBeingList.clear();
    }

    public static void remove(UUID id){
        dateOfLastChange = new Date();
        humanBeingList.remove(humanBeingCollection.get(id));
        humanBeingCollection.remove(id);
    }

    public static boolean hasElement(UUID id) {return humanBeingCollection.get(id) != null;}
    
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
