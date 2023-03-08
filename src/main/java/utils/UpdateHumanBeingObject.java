package utils;

import collection.Fields;
import collection.HumanBeing;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class UpdateHumanBeingObject {

    private final Reader reader;

    public UpdateHumanBeingObject(Reader reader) {
        this.reader = reader;
    }

    public void update(HumanBeing human, Fields field) {
        String valueField = reader.getNewLine();
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        Predicate<String> notNullSetter = notNullSetters.get(field);
        if(notNullSetter == null) updateNullSetter(human, field, valueField);
        else {
            boolean isCorrectField;
            do{
                System.out.println(field);
                String fieldValue = reader.getNewLine();
                isCorrectField = notNullSetters.get(field).test(fieldValue);
            }while (!isCorrectField);
        }


    }

    public void updateNullSetter(HumanBeing human, Fields field, String valueField){
        Map<Fields, Consumer<String>> setters = human.getSetters();
        setters.get(field).accept(valueField);
    }
}
