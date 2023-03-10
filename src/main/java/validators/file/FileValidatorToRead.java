package validators.file;

import validators.Errors;
import validators.Validator;

import java.io.File;

public class FileValidatorToRead extends NameFileValidator {

    public FileValidatorToRead(String FILE_PATH){
        super(FILE_PATH);
    }

    private final File file = new File(FILE_PATH);

    protected boolean isFileNotExist(){
        return !file.exists();
    }

    protected boolean isFileNotCanRead(){
        return !file.canRead();
    }

    @Override
    protected void addAllErrors(){
        addError(this::isFileNotExist, Errors.NOTEXISTFILE);
        addError(this::isFileNotCanRead, Errors.NOTCANREADFILE);
    }
}
