import validators.ValidatorEnv;
import validators.ValidatorNameFile;

public class Main {
    public static void main(String[] args) {
        ValidatorEnv validatorEnv = new ValidatorEnv(args.length);
        validatorEnv.validate();
        final String FILE_PATH = System.getenv(args[0]);
        ValidatorNameFile validatorNameFile = new ValidatorNameFile(FILE_PATH);
        validatorNameFile.validate();
    }
}
