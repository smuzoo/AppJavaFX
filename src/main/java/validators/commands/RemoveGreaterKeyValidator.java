package validators.commands;

import validators.Errors;
import validators.Validator;

import java.util.UUID;

public class RemoveGreaterKeyValidator extends Validator {

    protected String[] arguments;

    public RemoveGreaterKeyValidator(String[] arguments) { this.arguments = arguments; }

    protected boolean isLineNotHasOneArgument(){
        return arguments.length != 2;
    }

    protected boolean isNotCanTransformToUUID(){
        String id = arguments[1];
        try{
            UUID.fromString(id);
        }
        catch (IllegalArgumentException ex){
            return true;
        }
        return false;
    }

    @Override
    protected void addAllError() {
        addError(this::isLineNotHasOneArgument, Errors.NOTHASONEARGUMENT);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRANSFORMTOUUID);
    }
}
