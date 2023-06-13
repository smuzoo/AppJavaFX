package utils;

import collection.Fields;
import collection.Vehicle;
import utils.readers.Reader;
import validators.Errors;
import validators.Validator;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Creator human being object.
 */
public class CreatorHumanBeingObject {

    private final Reader reader;

    private Errors error;
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
    public Vehicle create(){
        Vehicle vehicle = new Vehicle();
        Map<Fields, Predicate<String>> notNullSetters = vehicle.getNotNullSetters();
        Map<Fields, Consumer<String>> setters = vehicle.getSetters();
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
        return vehicle;
    }

    public Vehicle create(String... fields){
        error = validate(fields[0], fields[1], fields[2]);
        Vehicle vehicle = new Vehicle();
        List<Predicate<String>> notNullSetters = new ArrayList<>(vehicle.getNotNullSetters().values());
        List<Consumer<String>> setters = new ArrayList<>(vehicle.getSetters().values());
        if(fields.length != (notNullSetters.size() + setters.size()))
            throw new RuntimeException("ERROR! Setters have a different number of fields than the one passed on");
        for(int i = 0; i < notNullSetters.size(); i ++){
            notNullSetters.get(i).test(fields[i]);
        }
        for(int i = 0; i < setters.size(); i++){
           setters.get(i).accept(fields[notNullSetters.size()+i]);
        }
        return vehicle;
    }

    private Errors validate(String name, String coordinates, String impactSpeed) {
        List<Validator> validators = new ArrayList<>();
        validators.add(new NameValidator(name));
        validators.add(new CoordinatesValidator(coordinates.split(",")));
        validators.add(new ImpactSpeedValidator(impactSpeed));
        Errors error = Errors.NOTHAVEERRORS;
        for (Validator validator : validators) {
            error = validator.validateAll();
            if (error != Errors.NOTHAVEERRORS) return error;
        }
        return error;

    }

    public Errors getError() {
        return error;
    }
}
