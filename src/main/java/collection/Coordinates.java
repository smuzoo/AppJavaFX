package collection;

/**
 * The type Coordinates.
 */
public class Coordinates {
    private float x; //Значение поля должно быть меньше -809
    private Integer y; //Поле не может быть null

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(float x, Integer y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get x float.
     *
     * @return the float
     */
    public float getX(){
        return x;
    }

    /**
     * Get y integer.
     *
     * @return the integer
     */
    public Integer getY(){
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}