package validators.commands;

import collection.HumanBeingCollection;
import validators.Errors;

import java.util.UUID;

public class RemoveElementValidator extends RemoveGreaterKeyValidator {

    public RemoveElementValidator(String argument) { super(argument); }

    protected boolean isNotHasElement(){
        UUID id = UUID.fromString(argument);
        return !HumanBeingCollection.hasElement(id);
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRANSFORMTOUUID);
        addError(this::isNotHasElement, Errors.NOTHASELEMENT);
    }
}
