package validators.fields;

import validators.Errors;
import validators.Validator;

public class NameValidator extends Validator {

    final private String name;

    public NameValidator(String name) {
        this.name = name;
    }

    private boolean isEmpty(){
        return name.trim().equals("");
    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
    }
}
