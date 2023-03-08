package collection;

public enum Fields {

    NAME("имя", "не может быть null"),
    COORDINATES("координаты", "введите через запятую coordinate.x,coordinate.y (поля float и Integer соответственно" +
            "поля не могут быть null, поле coordinate.x не должно превышать значение " + Fields.MAXIMUM_X),
    IMPACTSPEED("скорость удара", "может быть null, не должно превышать значение " + Fields.MAXIMUM_IMPACT_SPEED),
    REALHERO("настоящий герой", "по умолчанию false, при вводе 1 или true - поле становится true"),
    HASTOOTHPICK("ковыряется в зубах", "по умолчанию false, при вводе 1 или true - поле становится true"),
    WEAPONTYPE("тип оружия", "может быть " + WeaponType.getStringValues() + ", может быть null"),
    MOOD("муд", "может быть " + Mood.getStringValues() + ", может быть null");


    final static float MAXIMUM_X = -809f;
    final static int MAXIMUM_IMPACT_SPEED = 496;
    final String name, comments;

    Fields(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "Введите значение поля " + name + " : " + comments;
    }
}
