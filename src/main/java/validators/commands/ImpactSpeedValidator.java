package validators.commands;

import validators.Errors;
import validators.Validator;

public class ImpactSpeedValidator extends Validator {

    protected String argument;

    public ImpactSpeedValidator(String argument){
        this.argument = argument;
    }

    protected boolean isNotHasArgument(){
        return !argument.equals("");
    }

    protected boolean isNotCanTransformToInt(){
        try{
            Integer.parseInt(argument);
        }catch (NumberFormatException ex){
            return true;
        }
        return false;
    }

    @Override
    protected void addAllErrors(){
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToInt, Errors.NOTCANTRANSFORMTOINT);
    }
}

