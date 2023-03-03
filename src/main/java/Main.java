import collection.HumanBeingCollection;
import collection.Mood;
import commands.CommandController;
import validators.env.EnvValidator;
import validators.file.CSVFileValidator;
import validators.file.NameFileValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnvValidator envValidator = new EnvValidator(args.length);
        envValidator.validateWithExit();
        final String FILE_PATH = System.getenv(args[0]);
        NameFileValidator nameFileValidator = new NameFileValidator(FILE_PATH);
        nameFileValidator.validateWithExit();
        CSVFileValidator csvFileValidator = new CSVFileValidator(FILE_PATH);
        csvFileValidator.validateWithExit();
        HumanBeingCollection.readFile(FILE_PATH);
        CommandController commandController = new CommandController();
        commandController.init();
        System.out.println(Mood.valueOf("1"));
        Scanner scanner = new Scanner(System.in);
        String request;
        while(!((request = scanner.nextLine()).equals("exit"))){
            commandController.executeCommand(request);
        }

    }
}
