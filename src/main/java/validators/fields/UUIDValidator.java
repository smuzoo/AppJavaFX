package validators.fields;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

/**
 * The type Uuid validator.
 */
public class UUIDValidator extends Validator {

    /**
     * The Id.
     */
    protected final String id;

    /**
     * Instantiates a new Uuid validator.
     *
     * @param id the id
     */
    public UUIDValidator(String id) {
        this.id = id;
    }

    private boolean isEmpty(){
        return id.equals("");
    }

    private boolean isNotCanTransformToUUID(){
        try {
            UUID.fromString(id);
        }catch (IllegalArgumentException e){
            return true;
        }
        return false;
    }

    private boolean isUsed(){
        return HumanBeingCollection.getHuman(UUID.fromString(id)) != null;

    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRASFORMTOUUID);
    }

}
