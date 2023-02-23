package collection;

public class Coordinates {
    private float x; //Значение поля должно быть больше -809
    private Integer y; //Поле не может быть null
    public Coordinates(float x, Integer y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public Integer getY(){
        return y;
    }

}