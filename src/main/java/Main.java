import validators.env.EnvValidator;
import validators.file.CSVValidatorFile;
import validators.file.NameFileValidator;

public class Main {
    public static void main(String[] args) {
        EnvValidator envValidator = new EnvValidator(args.length);
        envValidator.validateWithExit();
        final String FILE_PATH = System.getenv(args[0]);
        NameFileValidator nameFileValidator = new NameFileValidator(FILE_PATH);
        nameFileValidator.validateWithExit();
        CSVValidatorFile csvValidatorFile = new CSVValidatorFile(FILE_PATH);
        csvValidatorFile.validateWithExit();
        System.out.println("All OK!");
    }
}
