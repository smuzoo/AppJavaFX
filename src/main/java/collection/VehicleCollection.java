package collection;

import Database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


/**
 * The type Human being collection.
 */
public class VehicleCollection {

    private static final Map<Long, Vehicle> vehicleCollection;
    private static final Date dateOfInitialization;
    private static Date dateOfLastChange;

    static {
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
        vehicleCollection = new HashMap<>();
    }

    /**
     * Read database.
     */
    public static void readFromDatabase(){
        Database db = Database.getInstance();
        ResultSet VehicleObject = db.getVehicles();
        try {
            while(VehicleObject.next()){
                Long id = VehicleObject.getLong(1);
                String name = VehicleObject.getString(2);
                float x = VehicleObject.getFloat(3);
                Integer y = VehicleObject.getInt(4);
                LocalDate creationDate = VehicleObject.getDate(5).toLocalDate();
                boolean realhero = VehicleObject.getBoolean(6);
                boolean hastoothpick = VehicleObject.getBoolean(7);
                Integer impactSpeed = VehicleObject.getInt(8);
                VehicleType vehicleType = VehicleType.getWeaponType(VehicleObject.getString(9));
                FuelType fuelType = FuelType.getMood(VehicleObject.getString(10));
                VehiclePublicity vehiclePublicity = new VehiclePublicity(VehicleObject.getBoolean(11));
                String userLogin = VehicleObject.getString(12);
                add(new Vehicle(id, name, new Coordinates(x, y), creationDate, realhero, hastoothpick, impactSpeed,
                        vehicleType, fuelType, vehiclePublicity, userLogin));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Add to HumanBeing collection.
     *
     * @param vehicle the vehicle
     */
    public static void add(Vehicle vehicle){
        dateOfLastChange = new Date();
        vehicleCollection.put(vehicle.getId(), vehicle);
    }

    /**
     * Clear HumanBeing collection.
     */
    public static void clear(){
        dateOfLastChange = new Date();
        vehicleCollection.clear();
    }

    /**
     * Remove HumanBeing object in HumanBeing collection by id.
     *
     * @param id the id
     */
    public static void remove(Long id){
        dateOfLastChange = new Date();
        vehicleCollection.remove(id);
    }

    /**
     * Get HumanBeing object in HumanBeing collection by id.
     *
     * @param id the id
     * @return the human being
     */
    public static Vehicle getVehicle(Long id){
        return vehicleCollection.get(id);
    }

    /**
     * Has element in HumanBeing collection by id.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean hasElement(Long id) {return vehicleCollection.get(id) != null;}

    /**
     * Get count HumanBeing collection long.
     *
     * @return the long
     */
    public static long getCountVehicleCollection(){ return vehicleCollection.size(); }

    /**
     * Get entry set HumanBeing collection.
     *
     * @return the set
     */
    public static Set<Map.Entry<Long, Vehicle>> getEntrySet(){
        return vehicleCollection.entrySet();
    }

    /**
     * Get values HumanBeing collection (HumanBeing).
     *
     * @return the collection
     */
    public static Collection<Vehicle> getVehicles(){
        return vehicleCollection.values();
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
    public static Map<Long, Vehicle> getVehicleCollection() {
        return vehicleCollection;
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
