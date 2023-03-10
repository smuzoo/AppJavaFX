package commands.specific;

import commands.Command;
import commands.CommandController;
import utils.readers.NameReader;
import utils.readers.Reader;
import utils.readers.ReaderFromFile;
import utils.readers.ReaderManager;
import validators.commands.ArgumentValidator;
import validators.file.FileValidatorToRead;

import static colors.Colors.*;

public class ExecuteScript implements Command {
    private final static int MAX_COUNT_RECURSION = 300;
    private static int countRecursion = 0;

    @Override
    public void execute(String FILE_PATH){
        countRecursion++;
        ArgumentValidator argumentValidator = new ArgumentValidator(FILE_PATH);
        FileValidatorToRead fileValidatorToRead = new FileValidatorToRead(FILE_PATH);
        if(argumentValidator.isValid() & fileValidatorToRead.isValid()){
            ReaderManager reader = new ReaderManager(new ReaderFromFile(FILE_PATH), NameReader.READERFILE);
            CommandController commandController = new CommandController(reader);
            while(reader.getNameReader() != NameReader.READERCONSOLE & countRecursion < MAX_COUNT_RECURSION){
                String request = reader.getNewLine();
                commandController.executeCommand(request);
            }

        }
    }

    @Override
    public String description(){
        return BLUE + "execute_script " + PURPLE + "file_name" + RESET + " : считать и исполнить скрипт из указанного файла. В скрипте содержатся" +
                " команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
