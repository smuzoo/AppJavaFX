import collection.HumanBeingCollection;
import commands.CommandController;
import utils.FileConstant;
import utils.readers.ReaderFromConsole;
import validators.env.EnvValidator;
import validators.file.FileValidatorToReadAndWrite;
import validators.file.NameFileValidator;

public class Main {
    public static void main(String[] args) {
        EnvValidator envValidator = new EnvValidator(args.length);
        envValidator.validateWithExit();
        final String FILE_PATH = System.getenv(args[0]);
        NameFileValidator nameFileValidator = new NameFileValidator(FILE_PATH);
        nameFileValidator.validateWithExit();
        FileValidatorToReadAndWrite fileValidatorToReadAndWrite = new FileValidatorToReadAndWrite(FILE_PATH);
        fileValidatorToReadAndWrite.validateWithExit();
        FileConstant.setFilePath(FILE_PATH);
        HumanBeingCollection.readFile(FILE_PATH);
        ReaderFromConsole reader = new ReaderFromConsole();
        CommandController commandController = new CommandController(reader);
        String request;
        while(!((request = reader.getNewLine()).equals("exit"))){
            commandController.executeCommand(request);
        }

    }
}
