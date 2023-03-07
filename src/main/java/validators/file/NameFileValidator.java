package validators.file;

import validators.Validator;
import validators.Errors;

public class NameFileValidator extends Validator {
    protected String FILE_PATH;
    public NameFileValidator(String env){
        this.FILE_PATH = env;
    }

    private boolean isOnNull() {
        return FILE_PATH == null;
    }

    private boolean isOnEmpty() {
        return FILE_PATH.equals("");
    }

    @Override
    protected void addAllErrors(){
        addError(this::isOnNull, Errors.WRONGENVIRONMENT);
        addError(this::isOnEmpty, Errors.EMPTYENVIRONMENT);
    }
}
