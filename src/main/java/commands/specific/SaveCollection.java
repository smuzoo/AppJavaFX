package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.FileConstant;
import validators.Errors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SaveCollection implements Command {

    @Override
    public void execute(String ignore){
        String FILE_PATH = FileConstant.getFilePath();
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
        return "save : сохранить коллекцию в файл";
    }
}
