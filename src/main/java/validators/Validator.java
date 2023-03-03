package validators;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Validator {
    private final Map<IsValidating, Errors> validatesMethods = new LinkedHashMap<>();

    abstract protected void addAllError();

    protected void addError(IsValidating method, Errors error){
        validatesMethods.put(method, error);
    }

    protected Errors validateAll(){
        addAllError();
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
