package validators.fields;

import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

/**
 * The type Uuid validator.
 */
public class IDValidator extends Validator {

    /**
     * The Id.
     */
    protected final String id;

    /**
     * Instantiates a new Uuid validator.
     *
     * @param id the id
     */
    public IDValidator(String id) {
        this.id = id;
    }

    private boolean isEmpty(){
        return id.equals("");
    }

    protected boolean isNotCanTransformToUUID(){
        String regex = "^-?\\d+$";
        return id.matches(regex);
    }

    private boolean isUsed(){
        return HumanBeingCollection.hasElement(Long.parseLong(id));

    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRASFORMTOUUID);
        addError(this::isUsed, Errors.USEDID);
    }

}
