package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.FileConstant;
import utils.readers.ReaderFromConsole;
import validators.Errors;
import validators.file.FileValidatorToReadAndWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static colors.Colors.*;


/**
 * The command Save collection.
 */
public class SaveCollection implements Command {

    @Override
    public void execute(String ignore){
        String FILE_PATH = FileConstant.getFilePath();
        ReaderFromConsole reader = new ReaderFromConsole();
        FileValidatorToReadAndWrite fileValidatorToReadAndWrite = new FileValidatorToReadAndWrite(FILE_PATH);
        while (!fileValidatorToReadAndWrite.isValid()){
            System.out.println("Введите название нового файла");
            FILE_PATH = reader.getNewLine();
            FileConstant.setFilePath(FILE_PATH);
            fileValidatorToReadAndWrite = new FileValidatorToReadAndWrite(FILE_PATH);
        }
        try(FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for(HumanBeing human : HumanBeingCollection.getHumanBeings()){
                bufferedWriter.write(human.getAllFieldsAsString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println(Errors.IMPOSSIBLEWRITETOFILE);
        }
        System.out.println("Коллекция была успешна записана в файл");
    }

    @Override
    public String description(){
        return BLUE + "save" + RESET + " : сохранить коллекцию в файл";
    }
}
