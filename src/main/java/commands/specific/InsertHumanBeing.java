package commands.specific;

import Database.Database;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.readers.Reader;
import validators.fields.IDValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static colors.Colors.*;

/**
 * The command Insert human being.
 */
public class InsertHumanBeing implements Command {

    private final Reader reader;


    /**
     * Instantiates a new Insert human being.
     *
     * @param reader the reader
     */
    public InsertHumanBeing(Reader reader){
        this.reader = reader;
    }

    @Override
    public void execute(String idArgument){
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        /* если id == null то UUID генерируется автоматически: не факт, что так должно быть, в ТЗ такого нет*/
        Long id = null;
        if(idArgument.equals("null")){
            Database db = Database.getInstance();
            ResultSet rs = db.getNewId("users_id_seq");
            try {
                rs.next();
                id = rs.getLong(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            db.closeConnection();

        }else{
            IDValidator idValidator = new IDValidator(idArgument);
            if(idValidator.isValid()){
                id = Long.parseLong(idArgument);
            }
        }
        if(id != null){
            HumanBeing human = creatorHumanBeingObject.create();
            human.setId(id);
            Database db = Database.getInstance();
            int update = db.addHumanBeingToDatabase("human_beings", human);
            if(update > 0){
                HumanBeingCollection.add(human);
                System.out.println("Элемент успешно добавлен в коллекцию");
            }
        }

    }

    @Override
    public String description(){
        return BLUE + "insert" + PURPLE +" null {element}" + RESET + " : добавить новый элемент с заданным ключом";
    }



}
