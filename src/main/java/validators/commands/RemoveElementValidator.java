package validators.commands;

import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

public class RemoveElementValidator extends Validator {
    private final String[] arguments;

    public RemoveElementValidator(String[] arguments) { this.arguments = arguments; }

    private boolean isLineNotHasOneArgument(){
        return arguments.length != 2;
    }

    private boolean isNotCanTransformToUUID(){
        String id = arguments[1];
        try{
            UUID.fromString(id);
        }
        catch (IllegalArgumentException ex){
            return true;
        }
        return false;
    }

    private boolean isNotHasElement(){
        UUID id = UUID.fromString(arguments[1]);
        return !HumanBeingCollection.hasElement(id);
    }

    @Override
    protected void addAllError() {
        addError(this::isLineNotHasOneArgument, Errors.NOTHASONEARGUMENT);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRANSFORMTOUUID);
        addError(this::isNotHasElement, Errors.NOTHASELEMENT);
    }
}
