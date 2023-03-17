package validators.fields;

import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

/**
 * The type Exist uuid validator.
 */
public class ExistUUIDValidator extends Validator {

    private final UUID id;

    /**
     * Instantiates a new Exist uuid validator.
     *
     * @param id the id
     */
    public ExistUUIDValidator(UUID id) {
        this.id = id;
    }

    private boolean isNotExistElement(){
        return !HumanBeingCollection.hasElement(id);
    }

    @Override
    public void addAllErrors(){
        addError(this::isNotExistElement, Errors.NOTHASELEMENT);
    }

}
