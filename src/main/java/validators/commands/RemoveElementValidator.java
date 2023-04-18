package validators.commands;

import collection.HumanBeingCollection;
import validators.Errors;

/**
 * The type Remove element validator.
 */
public class RemoveElementValidator extends RemoveGreaterKeyValidator {

    /**
     * Instantiates a new Remove element validator.
     *
     * @param argument the argument
     */
    public RemoveElementValidator(String argument) { super(argument); }

    /**
     * Is not has element boolean.
     *
     * @return the boolean
     */
    protected boolean isNotHasElement(){
        Long id = Long.parseLong(argument);
        return !HumanBeingCollection.hasElement(id);
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToID, Errors.NOTCANTRANSFORMTOUUID);
        addError(this::isNotHasElement, Errors.NOTHASELEMENT);
    }
}
