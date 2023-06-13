package collection;

/**
 * The enum Mood.
 */
public enum FuelType {
    /**
     * Longing mood.
     */
    GASOLINE("gasoline", "1", 0),
    /**
     * Gloom mood.
     * 
     */
    NUCLEAR("nuclear", "2", 1),
    /**
     * Apathy mood.
     */
    ALCOHOL("alcohol", "3", 2),
    /**
     * Rage mood.
     */
    KEROSENE("kerosene", "4", 50);

        private final String name, order;
        private final int power;


        FuelType(String name, String order, int power) {
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
            return name;
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
     * Gets mood for field name or order.
     *
     * @param s the s
     * @return the mood
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static FuelType getMood(String s) throws  IllegalArgumentException{
            FuelType[] fuelTypeValues = FuelType.values();
            for (FuelType fuelType : fuelTypeValues) {
                if (s.equals(fuelType.getName()) || s.equals(fuelType.getOrder())) {
                    return fuelType;
                }
            }
            return null;
        }

    /**
     * Gets string all values in Mood.
     *
     * @return the string values in Mood
     */
    public static String getStringValues() {
            StringBuilder sb = new StringBuilder();
            for (FuelType fuelType : FuelType.values()) {
                sb.append(fuelType.getOrder()).append(" - ").append(fuelType.getName()).append(" | ");
            }
            sb.delete(sb.length() - 3, sb.length()); // удаление последнего разделителя "| "
            return sb.toString();
        }


        @Override
        public String toString(){
            return this.getName();
        }






    }
