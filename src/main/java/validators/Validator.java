package validators;

import java.util.HashMap;

abstract public class Validator {
    protected HashMap<IsValidating, EnvErrors> validatesMethods = new HashMap<>();
    abstract void addValidError();
    public void validate(){
        addValidError();
        for(IsValidating method : validatesMethods.keySet()){
            if(method.isNotValidate()){
                System.out.println(validatesMethods.get(method));
            }
        }
    }
}
