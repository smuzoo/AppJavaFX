package utils;

import collection.Fields;
import collection.HumanBeing;
import javafx.util.Pair;
import utils.readers.Reader;
import validators.Errors;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Creator human being object.
 */
public class CreatorHumanBeingObject {

    private final Reader reader;

    /**
     * Instantiates a new Creator human being object.
     *
     * @param reader the reader
     */
    public CreatorHumanBeingObject(Reader reader){
        this.reader = reader;
    }

    /**
     * Create human being.
     *
     * @return the human being
     */
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

    public HumanBeing create(String... fields){
        HumanBeing humanBeing = new HumanBeing();
        List<Predicate<String>> notNullSetters = new ArrayList<>(humanBeing.getNotNullSetters().values());
        List<Consumer<String>> setters = new ArrayList<>(humanBeing.getSetters().values());
        if(fields.length != (notNullSetters.size() + setters.size()))
            throw new RuntimeException("ERROR! Setters have a different number of fields than the one passed on");
        for(int i = 0; i < notNullSetters.size(); i ++){
            notNullSetters.get(i).test(fields[i]);
        }
        for(int i = 0; i < setters.size(); i++){
           setters.get(i).accept(fields[notNullSetters.size()+i]);
        }
        return humanBeing;
    }


}
