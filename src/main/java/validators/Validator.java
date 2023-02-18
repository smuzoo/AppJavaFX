package validators;

import java.util.HashMap;

abstract public class Validator {
    protected HashMap<IsValidating, EnvErrors> validatesMethods = new HashMap<>();

    abstract void addValidError();
    abstract void validate();

    protected EnvErrors validateAll(){
        addValidError();
        for(IsValidating method : validatesMethods.keySet()){
            if(method.isNotValidate()){
                return validatesMethods.get(method);
            }
        }
        return EnvErrors.NOTHAVEERRORS;
    }

    protected void printError(EnvErrors error){
        System.out.println(error);
    }

    protected void printErrorWithExit(EnvErrors error){
        printError(error);
        System.exit(0);
    }






}
