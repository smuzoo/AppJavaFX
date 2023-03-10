package collection;

    public enum Mood {
        LONGING("LONGING", "1", 0),
        GLOOM("GLOOM", "2", 1),
        APATHY("APATHY", "3", 2),
        RAGE("RAGE", "4", 50);

        private final String name, order;
        private final int power;


        Mood(String name, String order, int power) {
            this.name = name;
            this.order = order;
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public String getOrder() {
            return order;
        }

        public int getPower() {
            return power;
        }

        public static Mood getMood(String s) throws  IllegalArgumentException{
            Mood[] moodValues = Mood.values();
            for (Mood mood : moodValues) {
                if (s.equals(mood.getName()) || s.equals(mood.getOrder())) {
                    return mood;
                }
            }
            return null;
        }

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
