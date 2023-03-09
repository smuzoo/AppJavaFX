package collection;

import utils.ReaderFromFileToCollection;

import java.util.*;

public class HumanBeingCollection {

    private static Map<UUID, HumanBeing> humanBeingCollection;
    private final static Date dateOfInitialization;
    private static Date dateOfLastChange;

    static {
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public static void readFile(String FILE_PATH){
        ReaderFromFileToCollection reader = new ReaderFromFileToCollection(FILE_PATH);
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
        dateOfLastChange = new Date();
        humanBeingCollection.remove(id);
    }

    public static HumanBeing getHuman(UUID id){
        return humanBeingCollection.get(id);
    }

    public static boolean hasElement(UUID id) {return humanBeingCollection.get(id) != null;}
    
    public static long getCountHumanBeingCollection(){ return humanBeingCollection.size(); }

    public static Set<UUID> getUUIDs(){
        return humanBeingCollection.keySet();
    }

    public static Set<Map.Entry<UUID, HumanBeing>> getEntrySet(){
        return humanBeingCollection.entrySet();
    }

    public static Collection<HumanBeing> getHumanBeings(){
        return humanBeingCollection.values();
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
