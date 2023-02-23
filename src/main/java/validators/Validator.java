package validators;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Validator {
    protected Map<IsValidating, Errors> validatesMethods = new LinkedHashMap<>();

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


    public boolean isValid(){
        Errors error = validateAll();
        if(error != Errors.NOTHAVEERRORS){
            System.out.println(error);
            return false;
        }
        return true;
    }

    public void validateWithExit(){
        boolean validation = isValid();
        if(!validation)System.exit(130);
    }
}
