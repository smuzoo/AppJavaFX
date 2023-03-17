package collection;

import utils.readers.ReaderFromFileToCollection;

import java.util.*;


/**
 * The type Human being collection.
 */
public class HumanBeingCollection {

    private static Map<UUID, HumanBeing> humanBeingCollection;
    private final static Date dateOfInitialization;
    private static Date dateOfLastChange;

    static {
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    /**
     * Read file.
     *
     * @param FILE_PATH the file path
     */
    public static void readFile(String FILE_PATH){
        ReaderFromFileToCollection reader = new ReaderFromFileToCollection(FILE_PATH);
        humanBeingCollection = reader.read();
    }

    /**
     * Add to HumanBeing collection.
     *
     * @param human the human
     */
    public static void add(HumanBeing human){
        dateOfLastChange = new Date();
        humanBeingCollection.put(human.getId(), human);
    }

    /**
     * Clear HumanBeing collection.
     */
    public static void clear(){
        dateOfLastChange = new Date();
        humanBeingCollection.clear();
    }

    /**
     * Remove HumanBeing object in HumanBeing collection by id.
     *
     * @param id the id
     */
    public static void remove(UUID id){
        dateOfLastChange = new Date();
        humanBeingCollection.remove(id);
    }

    /**
     * Get HumanBeing object in HumanBeing collection by id.
     *
     * @param id the id
     * @return the human being
     */
    public static HumanBeing getHuman(UUID id){
        return humanBeingCollection.get(id);
    }

    /**
     * Has element in HumanBeing collection by id.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean hasElement(UUID id) {return humanBeingCollection.get(id) != null;}

    /**
     * Get count HumanBeing collection long.
     *
     * @return the long
     */
    public static long getCountHumanBeingCollection(){ return humanBeingCollection.size(); }

    /**
     * Get entry set HumanBeing collection.
     *
     * @return the set
     */
    public static Set<Map.Entry<UUID, HumanBeing>> getEntrySet(){
        return humanBeingCollection.entrySet();
    }

    /**
     * Get values HumanBeing collection (HumanBeing).
     *
     * @return the collection
     */
    public static Collection<HumanBeing> getHumanBeings(){
        return humanBeingCollection.values();
    }

    /**
     * Gets date of last change.
     *
     * @return the date of last change
     */
    public static Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    /**
     * Gets HumanBeing collection.
     *
     * @return the HumanBeing collection
     */
    public static Map<UUID, HumanBeing> getHumanBeingCollection() {
        return humanBeingCollection;
    }

    /**
     * Gets date of initialization.
     *
     * @return the date of initialization
     */
    public static Date getDateOfInitialization() {
        return dateOfInitialization;
    }
}
