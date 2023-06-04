package collection;

/**
 * The enum Mood.
 */
public enum Mood {
    /**
     * Longing mood.
     */
    LONGING("longing", "1", 0),
    /**
     * Gloom mood.
     */
    GLOOM("gloom", "2", 1),
    /**
     * Apathy mood.
     */
    APATHY("apathy", "3", 2),
    /**
     * Rage mood.
     */
    RAGE("rage", "4", 50);

        private final String name, order;
        private final int power;


        Mood(String name, String order, int power) {
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
    public static Mood getMood(String s) throws  IllegalArgumentException{
            Mood[] moodValues = Mood.values();
            for (Mood mood : moodValues) {
                if (s.equals(mood.getName()) || s.equals(mood.getOrder())) {
                    return mood;
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
            for (Mood mood : Mood.values()) {
                sb.append(mood.getOrder()).append(" - ").append(mood.getName()).append(" | ");
            }
            sb.delete(sb.length() - 3, sb.length()); // удаление последнего разделителя "| "
            return sb.toString();
        }


        @Override
        public String toString(){
            return this.getName();
        }






    }
