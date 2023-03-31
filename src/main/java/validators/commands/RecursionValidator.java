package validators.commands;

import validators.Errors;
import validators.Validator;

import java.util.List;

/**
 * The type Recursion validator.
 */
public class RecursionValidator extends Validator {

    private final String nameFile;
    private final List<String> historyFiles;

    /**
     * Instantiates a new Recursion validator.
     *
     * @param nameFile     the nameFile
     * @param historyFiles the historyFiles
     */
    public RecursionValidator(String nameFile, List<String> historyFiles) {
        this.nameFile = nameFile;
        this.historyFiles = historyFiles;
    }

    private boolean isRecursion(){
        System.out.println(historyFiles);
        System.out.println(nameFile);
        return historyFiles.contains(nameFile);
    }

    @Override
    public void addAllErrors(){
        addError(this::isRecursion, Errors.RECURSION);
    }
}
