package validators.env;

import validators.Errors;
import validators.Validator;

public class EnvValidator extends Validator {
    private final int lenghtArgs;
    public EnvValidator(int lenghtArgs){
        this.lenghtArgs = lenghtArgs;
    }
    public boolean validateOnHaveEnvironment(){
        return lenghtArgs == 0;
    }

    @Override
    protected void addAllErrors(){
        addError(this::validateOnHaveEnvironment, Errors.NOTHAVEENVIRONMENT);
    }
}
