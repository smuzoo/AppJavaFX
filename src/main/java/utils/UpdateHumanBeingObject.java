package utils;

import collection.Fields;
import collection.Vehicle;
import utils.readers.Reader;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Update human being object.
 */
public class UpdateHumanBeingObject {

    private final Reader reader;

    /**
     * Instantiates a new Update human being object.
     *
     * @param reader the reader
     */
    public UpdateHumanBeingObject(Reader reader) {
        this.reader = reader;
    }

    /**
     * Update fields in HumanBeing object.
     *
     * @param human the human
     * @param field the field
     * @return the human being
     */
    public Vehicle update(Vehicle human, Fields field) {
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        System.out.println(field);
        String valueField = reader.getNewLine();
        Predicate<String> notNullSetter = notNullSetters.get(field);
        if(notNullSetter == null) updateNullSetter(human, field, valueField);
        else {
            if(!notNullSetters.get(field).test(valueField)){
                System.out.println(field);
                valueField = reader.getNewLine();
            }
        }
        return human;


    }

    /**
     * Update fields that may null in HumanBeing object.
     *
     * @param human      the human
     * @param field      the field
     * @param valueField the value field
     */
    public void updateNullSetter(Vehicle human, Fields field, String valueField){
        Map<Fields, Consumer<String>> setters = human.getSetters();
        setters.get(field).accept(valueField);
    }

}
