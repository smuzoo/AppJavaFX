package validators.file;

import validators.Errors;

import java.io.File;

public class CSVFileValidator extends NameFileValidator {
    public CSVFileValidator(String FILE_PATH){
        super(FILE_PATH);
    }

    private final File file = new File(FILE_PATH);

    private boolean isFileExist(){
        return !file.exists();
    }

    private boolean isFileCanRead(){
        return !file.canRead();
    }

    private boolean isFileCanWrite(){
        return !file.canWrite();
    }

    private boolean isFileCSV(){
        return !file.getName().toLowerCase().endsWith(".csv");
    }

    @Override
    protected void addValidError(){
        validatesMethods.put(this::isFileExist, Errors.NOTEXISTFILE);
        validatesMethods.put(this::isFileCanRead, Errors.NOTCANREADFILE);
        validatesMethods.put(this::isFileCanWrite, Errors.NOTCANWRITEFILE);
        validatesMethods.put(this::isFileCSV, Errors.NOTCSVFILE);
    }
}
