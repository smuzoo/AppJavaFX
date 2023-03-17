package validators.file;

import validators.Validator;
import validators.Errors;

/**
 * The type Name file validator.
 */
public class NameFileValidator extends Validator {
    /**
     * The File path.
     */
    protected String FILE_PATH;

    /**
     * Instantiates a new Name file validator.
     *
     * @param env the env
     */
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
