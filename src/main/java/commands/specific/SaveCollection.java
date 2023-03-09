package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.FileConstant;
import utils.ReaderFromConsole;
import validators.Errors;
import validators.file.CSVFileValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static colors.Colors.*;


public class SaveCollection implements Command {

    @Override
    public void execute(String ignore){
        String FILE_PATH = FileConstant.getFilePath();
        ReaderFromConsole reader = new ReaderFromConsole();
        CSVFileValidator csvFileValidator = new CSVFileValidator(FILE_PATH);
        while (!csvFileValidator.isValid()){
            System.out.println("Введите название нового файла");
            FILE_PATH = reader.getNewLine();
            FileConstant.setFilePath(FILE_PATH);
            csvFileValidator = new CSVFileValidator(FILE_PATH);
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
