package validators.commands;

import validators.Errors;
import validators.Validator;
import java.util.UUID;

/**
 * The type Remove greater key validator.
 */
public class RemoveGreaterKeyValidator extends Validator {

    /**
     * The Argument.
     */
    protected String argument;

    /**
     * Instantiates a new Remove greater key validator.
     *
     * @param argument the argument
     */
    public RemoveGreaterKeyValidator(String argument) { this.argument = argument; }

    /**
     * Is not has argument boolean.
     *
     * @return the boolean
     */
    protected boolean isNotHasArgument(){
        return argument.equals("");
    }

    /**
     * Is not can transform to uuid boolean.
     *
     * @return the boolean
     */
    protected boolean isNotCanTransformToUUID(){
        String id = argument;
        try{
            UUID.fromString(id);
        }
        catch (IllegalArgumentException ex){
            return true;
        }
        return false;
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRANSFORMTOUUID);
    }
}
