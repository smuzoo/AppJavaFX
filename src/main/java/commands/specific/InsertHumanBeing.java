package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.Reader;
import utils.ReaderFromConsole;
import validators.fields.UUIDValidator;

import java.util.UUID;

public class InsertHumanBeing implements Command {

    private final Reader reader;

    public InsertHumanBeing(Reader reader){
        this.reader = reader;
    }

    @Override
    public void execute(String idArgument){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        /* если id == null то UUID генерируется автоматически: не факт, что так должно быть, в ТЗ такого нет*/
        UUID id = null;
        if(idArgument.equals("null")){
            id = UUID.randomUUID();
        }else{
            UUIDValidator uuidValidator = new UUIDValidator(idArgument);
            if(uuidValidator.isValid()){
                id = UUID.fromString(idArgument);
            }
        }
        if(id != null){
            HumanBeing human = creatorHumanBeingObject.create();
            human.setId(id);
            HumanBeingCollection.add(human);
        }

    }

    @Override
    public String description(){
        return "insert null {element} : добавить новый элемент с заданным ключом";
    }



}
