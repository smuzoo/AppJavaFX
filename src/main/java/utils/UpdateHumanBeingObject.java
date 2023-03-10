package utils;

import collection.Fields;
import collection.HumanBeing;
import utils.readers.Reader;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class UpdateHumanBeingObject {

    private final Reader reader;

    public UpdateHumanBeingObject(Reader reader) {
        this.reader = reader;
    }

    public void update(HumanBeing human, Fields field) {
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        System.out.println(field);
        String valueField = reader.getNewLine();
        Predicate<String> notNullSetter = notNullSetters.get(field);
        if(notNullSetter == null) updateNullSetter(human, field, valueField);
        else {
            while(!notNullSetters.get(field).test(valueField)){
                System.out.println(field);
                valueField = reader.getNewLine();
            }
        }


    }

    public void updateNullSetter(HumanBeing human, Fields field, String valueField){
        Map<Fields, Consumer<String>> setters = human.getSetters();
        setters.get(field).accept(valueField);
    }
}
