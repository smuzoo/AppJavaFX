package collection;

/**
 * The type Car.
 */
public class Car {
    private boolean cool;

    /**
     * Instantiates a new Car.
     */
    public Car(){}

    /**
     * Instantiates a new Car.
     *
     * @param cool the cool
     */
    public Car(boolean cool){
        this.cool = cool;
    }

    /**
     * Get status boolean.
     *
     * @return the boolean
     */
    public boolean getStatus(){
        return cool;
    }


}
