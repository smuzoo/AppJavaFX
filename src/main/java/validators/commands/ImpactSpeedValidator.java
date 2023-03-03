package validators.commands;

import validators.Errors;
import validators.Validator;

public class ImpactSpeedValidator extends Validator {

    protected String[] arguments;

    public ImpactSpeedValidator(String[] arguments){
        this.arguments = arguments;
    }

    protected boolean isLineNotHasOneArgument(){
        return arguments.length != 2;
    }

    protected boolean isNotCanTransformToInt(){
        try{
            Integer.parseInt(arguments[1]);
        }catch (NumberFormatException ex){
            return true;
        }
        return false;
    }

    @Override
    protected void addAllError(){
        addError(this::isLineNotHasOneArgument, Errors.NOTHASONEARGUMENT);
        addError(this::isNotCanTransformToInt, Errors.NOTCANTRANSFORMTOINT);
    }
}

