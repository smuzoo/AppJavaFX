package validators.fields;

import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

/**
 * The type Exist uuid validator.
 */
public class ExistIDValidator extends Validator {

    private final String id;

    /**
     * Instantiates a new Exist uuid validator.
     *
     * @param id the id
     */
    public ExistIDValidator(String id) {
        this.id = id;
    }

    private boolean isEmpty(){
        return id.equals("");
    }

    protected boolean isNotCanTransformToUUID(){
        String regex = "^-?\\d+$";
        return id.matches(regex);
    }

    private boolean isNotExistElement(){
        return !HumanBeingCollection.hasElement(Long.parseLong(id));
    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRASFORMTOUUID);
        addError(this::isNotExistElement, Errors.NOTHASELEMENT);
    }

}
