package validators.commands;

import validators.Errors;
import validators.Validator;

public class ArgumentValidator extends Validator {

    protected String argument;

    public ArgumentValidator(String argument){
        this.argument = argument;
    }

    protected boolean isNotHasArgument(){
        return argument.equals("");
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
    }
}
