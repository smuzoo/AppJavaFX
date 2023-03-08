package validators.fields;

import validators.Errors;
import validators.Validator;

import java.util.UUID;

public class UUIDValidator extends Validator {

    private final String id;

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

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRASFORMTOUUID);
    }

}
