package collection;

    public enum Mood {
        LONGING("LONGING", "1"),
        GLOOM("GLOOM", "2"),
        APATHY("APATHY", "3"),
        RAGE("RAGE", "4");

        private final String name, order;


        Mood(String name, String order) {
            this.name = name;
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public String getOrder() {
            return order;
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

        @Override
        public String toString(){
            return this.getName();
        }




    }
