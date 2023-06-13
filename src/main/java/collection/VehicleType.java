package collection;

/**
 * The enum Weapon type.
 */
public enum VehicleType {
    /**
     * Hammer weapon type.
     */
    HAMMER("car", "1", 15),
    /**
     * Pistol weapon type.
     */
    BOAT("boat", "2", 45),
    /**
     * Shotgun weapon type.
     */
    MOROTCYCLE("motorcycle", "3", 5),
    CHOPPER("chopper", "4", 10),
    SHIP("ship", "5", 80);

    private final String name, order;

    private final int power;

    VehicleType(String name, String order, int power) {
        this.name = name;
        this.order = order;
        this.power = power;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return (name == null ? "null" : name);
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Gets power.
     *
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * Gets weapon type for name or order.
     *
     * @param s the string(name or order)
     * @return the weapon type
     */
    public static VehicleType getWeaponType(String s) {
        VehicleType[] vehicleTypesValues = VehicleType.values();
        for (VehicleType vehicleType : vehicleTypesValues) {
            if (s.equals(vehicleType.getName()) || s.equals(vehicleType.getOrder())) {
                return vehicleType;
            }
        }
        return null;
    }

    /**
     * Gets string values in WeaponType.
     *
     * @return the string values in WeaponType
     */
    public static String getStringValues() {
        StringBuilder sb = new StringBuilder();
        for (VehicleType vehicleType : VehicleType.values()) {
            sb.append(vehicleType.getOrder()).append(" - ").append(vehicleType.getName()).append(" | ");
        }
        sb.delete(sb.length() - 3, sb.length()); // удаление последнего разделителя "| "
        return sb.toString();
    }

    @Override
    public String toString(){
        return getName();
    }

}