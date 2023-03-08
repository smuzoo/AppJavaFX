package utils;

import collection.Fields;
import collection.HumanBeing;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CreatorHumanBeingObject {

    private final Reader reader;

    public CreatorHumanBeingObject(Reader reader){
        this.reader = reader;
    }

    public HumanBeing create(){
        HumanBeing humanBeing = new HumanBeing();
        Map<Fields, Predicate<String>> notNullSetters = humanBeing.getNotNullSetters();
        Map<Fields, Consumer<String>> setters = humanBeing.getSetters();
        boolean isCorrectField;
        for(Fields field : notNullSetters.keySet()){
            do{
                System.out.println(field);
                String fieldValue = reader.getNewLine();
                isCorrectField = notNullSetters.get(field).test(fieldValue);
            }while (!isCorrectField);

        }
        for(Fields field : setters.keySet()) {
            System.out.println(field);
            setters.get(field).accept(reader.getNewLine());
        }
        return humanBeing;
    }


}
