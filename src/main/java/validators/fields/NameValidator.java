package validators.fields;

import validators.Errors;
import validators.Validator;

public class NameValidator extends Validator {

    final private String name;

    public NameValidator(String name) {
        this.name = name;
    }

    private boolean isNotEmpty(){
        return !name.equals("");
    }

    @Override
    public void addAllErrors(){
        addError(this::isNotEmpty, Errors.EMPTYFIELD);
    }
}
