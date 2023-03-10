package validators.file;

import validators.Errors;

import java.io.File;

public class FileValidatorToReadAndWrite extends FileValidatorToRead {
    public FileValidatorToReadAndWrite(String FILE_PATH){
        super(FILE_PATH);
    }

    private final File file = new File(FILE_PATH);

    private boolean isFileNotCanWrite(){
        return !file.canWrite();
    }

    @Override
    protected void addAllErrors(){
        addError(this::isFileNotExist, Errors.NOTEXISTFILE);
        addError(this::isFileNotCanRead, Errors.NOTCANREADFILE);
        addError(this::isFileNotCanWrite, Errors.NOTCANWRITEFILE);
    }
}
