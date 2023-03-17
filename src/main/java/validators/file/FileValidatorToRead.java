package validators.file;

import validators.Errors;
import validators.Validator;

import java.io.File;

/**
 * The type File validator to read.
 */
public class FileValidatorToRead extends NameFileValidator {

    /**
     * Instantiates a new File validator to read.
     *
     * @param FILE_PATH the file path
     */
    public FileValidatorToRead(String FILE_PATH){
        super(FILE_PATH);
    }

    private final File file = new File(FILE_PATH);

    /**
     * Is file not exist boolean.
     *
     * @return the boolean
     */
    protected boolean isFileNotExist(){
        return !file.exists();
    }

    /**
     * Is file not can read boolean.
     *
     * @return the boolean
     */
    protected boolean isFileNotCanRead(){
        return !file.canRead();
    }

    @Override
    protected void addAllErrors(){
        addError(this::isFileNotExist, Errors.NOTEXISTFILE);
        addError(this::isFileNotCanRead, Errors.NOTCANREADFILE);
    }
}
