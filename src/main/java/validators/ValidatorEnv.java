package validators;

import java.util.HashMap;

public class ValidatorEnv extends ValidatorWithExit{
    private int lenghtArgs;
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
}
