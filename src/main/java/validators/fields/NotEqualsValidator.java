package validators.fields;

import validators.Errors;
import validators.Validator;

import java.util.Arrays;

public class NotEqualsValidator extends Validator {

    private String[] needEquals;
    private String request;

    public NotEqualsValidator(String request, String... needEquals){
        this.request = request;
        this.needEquals = needEquals;
    }

    private boolean isNotEquals(){
        return !Arrays.stream(needEquals).anyMatch(str -> str.equals(request));
    }

    public void addAllErrors(){
        addError(this::isNotEquals, Errors.ISNOTEXISTENTOPTION);
    }

}
