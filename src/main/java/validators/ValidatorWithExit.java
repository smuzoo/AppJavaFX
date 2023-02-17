package validators;

public class ValidatorWithExit extends Validator{
    @Override
    public void validate(){
        addValidError();
        for(IsValidating method : validatesMethods.keySet()){
            if(method.isNotValidate()){
                System.out.println(validatesMethods.get(method));
                System.exit(130);
            }
        }
    }
    void addValidError(){}
}
