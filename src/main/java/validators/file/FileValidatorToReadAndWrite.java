package validators.file;

import validators.Errors;

import java.io.File;

/**
 * The type File validator to read and write.
 */
public class FileValidatorToReadAndWrite extends FileValidatorToRead {
    /**
     * Instantiates a new File validator to read and write.
     *
     * @param FILE_PATH the file path
     */
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
