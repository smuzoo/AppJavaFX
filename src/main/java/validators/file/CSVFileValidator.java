package validators.file;

import validators.Errors;

import java.io.File;

public class CSVFileValidator extends NameFileValidator {
    public CSVFileValidator(String FILE_PATH){
        super(FILE_PATH);
    }

    private final File file = new File(FILE_PATH);

    private boolean isFileNotExist(){
        return !file.exists();
    }

    private boolean isFileNotCanRead(){
        return !file.canRead();
    }

    private boolean isFileNotCanWrite(){
        return !file.canWrite();
    }

    private boolean isFileNotCSV(){
        return !file.getName().toLowerCase().endsWith(".csv");
    }

    @Override
    protected void addAllErrors(){
        addError(this::isFileNotExist, Errors.NOTEXISTFILE);
        addError(this::isFileNotCanRead, Errors.NOTCANREADFILE);
        addError(this::isFileNotCanWrite, Errors.NOTCANWRITEFILE);
        addError(this::isFileNotCSV, Errors.NOTCSVFILE);
    }
}
