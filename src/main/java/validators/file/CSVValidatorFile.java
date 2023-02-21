package validators.file;

import validators.Errors;

import java.io.File;

public class CSVValidatorFile extends NameFileValidator {
    public CSVValidatorFile(String FILE_PATH){
        super(FILE_PATH);
    }

    private File file = new File(FILE_PATH);

    private boolean isFileExist(){
        //System.out.println("I`m here");
        return !file.exists();
    }

    private boolean isFileCanRead(){
        //System.out.println("I`m in read");
        return !file.canRead();
    }

    private boolean isFileCanWrite(){
        //System.out.println("I`m in write");
        return !file.canWrite();
    }

    private boolean isFileCSV(){
        //System.out.println("I`m in csv");
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
