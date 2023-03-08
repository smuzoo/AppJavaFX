package collection;

import validators.Errors;

public enum Fields {

    NAME("имя", "не может быть null", 1),
    COORDINATES("координаты", "введите через запятую coordinate.x,coordinate.y (поля float и Integer соответственно" +
            "поля не могут быть null, поле coordinate.x не должно превышать значение " + Fields.MAXIMUM_X, 2),
    IMPACTSPEED("скорость удара", "может быть null, не должно превышать значение " + Fields.MAXIMUM_IMPACT_SPEED, 3),
    REALHERO("настоящий герой", "по умолчанию false, при вводе 1 или true - поле становится true", 4),
    HASTOOTHPICK("ковыряется в зубах", "по умолчанию false, при вводе 1 или true - поле становится true", 5),
    WEAPONTYPE("тип оружия", "может быть " + WeaponType.getStringValues() + ", может быть null", 6),
    MOOD("муд", "может быть " + Mood.getStringValues() + ", может быть null", 7),
    CARCOOL("наличие крутой машины", "по умолчанию false, при вводе 1 или true - поле становится true", 8);


    public final static float MAXIMUM_X = -809f;
    public final static int MAXIMUM_IMPACT_SPEED = 496;
    final String name, comments;
    final int order;

    Fields(String name, String comments, int order) {
        this.name = name;
        this.comments = comments;
        this.order = order;
    }

    public static Fields getForOrder(int order){
        for(Fields field : Fields.values()){
            if(field.order == order) return  field;
        }
        System.out.println(Errors.NOTHASFIELD);
        return null;
    }

    @Override
    public String toString(){
        return "Введите значение поля " + name + " : " + comments;
    }
}
