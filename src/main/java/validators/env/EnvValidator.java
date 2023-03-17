package validators.env;

import validators.Errors;
import validators.Validator;

/**
 * The type Env validator.
 */
public class EnvValidator extends Validator {
    private final int lenghtArgs;

    /**
     * Instantiates a new Env validator.
     *
     * @param lenghtArgs the lenght args
     */
    public EnvValidator(int lenghtArgs){
        this.lenghtArgs = lenghtArgs;
    }

    /**
     * Validate on have environment boolean.
     *
     * @return the boolean
     */
    public boolean validateOnHaveEnvironment(){
        return lenghtArgs == 0;
    }

    @Override
    protected void addAllErrors(){
        addError(this::validateOnHaveEnvironment, Errors.NOTHAVEENVIRONMENT);
    }
}
