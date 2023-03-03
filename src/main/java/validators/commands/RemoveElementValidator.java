package validators.commands;

import collection.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

import java.util.UUID;

public class RemoveElementValidator extends RemoveGreaterKeyValidator {

    public RemoveElementValidator(String[] arguments) { super(arguments); }

    protected boolean isNotHasElement(){
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
