package validators;

import java.util.HashMap;

abstract public class Validator {
    protected HashMap<IsValidating, Errors> validatesMethods = new HashMap<>();

    abstract protected void addValidError();

    protected Errors validateAll(){
        addValidError();
        for(IsValidating method : validatesMethods.keySet()){
            if(method.isNotValidate()){
                return validatesMethods.get(method);
            }
        }
        return Errors.NOTHAVEERRORS;
    }


    public void validate(){
        Errors error = validateAll();
        if(error != Errors.NOTHAVEERRORS){
            System.out.println(error);
        }
    }

    public void validateWithExit(){
        Errors error = validateAll();
        if(error != Errors.NOTHAVEERRORS){
            System.out.println(error);
            System.exit(130);
        }
    }
}
