package validators.commands;

import commands.specific.ExecuteScriptLogger;
import validators.Errors;
import validators.Validator;

import java.util.List;

/**
 * The type Recursion validator.
 */
public class RecursionValidator extends Validator {

    private final String nameFile;

    /**
     * Instantiates a new Recursion validator.
     *
     * @param nameFile the name file
     */
    public RecursionValidator(String nameFile) {
        this.nameFile = nameFile;
    }

    private boolean isRecursion(){
        return ExecuteScriptLogger.contains(nameFile);
    }

    @Override
    public void addAllErrors(){
        addError(this::isRecursion, Errors.RECURSION);
    }
}
