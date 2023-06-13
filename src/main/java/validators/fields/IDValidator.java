package validators.fields;

import collection.VehicleCollection;
import validators.Errors;
import validators.Validator;

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

    /**
     * Is not can transform to id boolean.
     *
     * @return the boolean
     */
    protected boolean isNotCanTransformToID(){
        String regex = "^-?[0-9]+$";
        return !id.matches(regex);
    }

    private boolean isUsed(){
        return VehicleCollection.hasElement(Long.parseLong(id));

    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToID, Errors.NOTCANTRANSFORMTOINT);
        addError(this::isUsed, Errors.USEDID);
    }

}
