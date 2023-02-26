import collection.HumanBeing;
import commands.CommandController;
import utils.ReaderHumanBeingCollection;
import validators.env.EnvValidator;
import validators.file.CSVFileValidator;
import validators.file.NameFileValidator;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        EnvValidator envValidator = new EnvValidator(args.length);
        envValidator.validateWithExit();
        final String FILE_PATH = System.getenv(args[0]);
        NameFileValidator nameFileValidator = new NameFileValidator(FILE_PATH);
        nameFileValidator.validateWithExit();
        CSVFileValidator csvFileValidator = new CSVFileValidator(FILE_PATH);
        csvFileValidator.validateWithExit();
        ReaderHumanBeingCollection readerHumanBeingCollection = new ReaderHumanBeingCollection(FILE_PATH);
        Map<UUID, HumanBeing> humanBeingCollection = readerHumanBeingCollection.read();
        CommandController commandController = new CommandController(humanBeingCollection);
        commandController.init();

        Scanner scanner = new Scanner(System.in);
        String request;
        while(!((request = scanner.nextLine()).equals("exit"))){
            commandController.executeCommand(request);
        }

    }
}
