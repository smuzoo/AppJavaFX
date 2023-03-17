package utils;

import collection.Fields;
import collection.HumanBeing;
import utils.readers.Reader;

import java.util.Map;
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


}
