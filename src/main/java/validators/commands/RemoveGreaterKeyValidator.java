package validators.commands;

import validators.Errors;
import validators.Validator;

import java.util.Objects;
import java.util.UUID;

public class RemoveGreaterKeyValidator extends Validator {

    protected String argument;

    public RemoveGreaterKeyValidator(String argument) { this.argument = argument; }

    protected boolean isNotHasArgument(){
        return !argument.equals("");
    }

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
