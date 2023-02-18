package validators;



public class ValidatorEnv extends Validator{
    private final int lenghtArgs;
    public ValidatorEnv(int lenghtArgs){
        this.lenghtArgs = lenghtArgs;
    }
    public boolean validateOnHaveEnvironment(){
        return lenghtArgs == 0;
    }

    @Override
    protected void addValidError(){
        validatesMethods.put(this::validateOnHaveEnvironment, EnvErrors.NOTHAVEENVIRONMENT);
    }

    @Override
    public void validate(){
        EnvErrors error = validateAll();
        if(error != EnvErrors.NOTHAVEERRORS) printErrorWithExit(error);
    }
}
