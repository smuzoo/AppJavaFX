package commands.specific;

import Database.Database;
import collection.Vehicle;
import collection.VehicleCollection;
import commands.Command;
import utils.CreatorHumanBeingObject;
import utils.readers.Reader;
import validators.Errors;
import validators.Validator;
import validators.fields.CoordinatesValidator;
import validators.fields.IDValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public InsertHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String idArgument) {
        CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
        /* если id == null то UUID генерируется автоматически: не факт, что так должно быть, в ТЗ такого нет*/


        Vehicle human = creatorHumanBeingObject.create();
        boolean addStatus = addToDb(idArgument, human);
        if (addStatus) System.out.println("Элемент успешно добавлен в коллекцию");
    }


    @Override
    public String description() {
        return "insert" + " null {element}" + " : добавить новый элемент с заданным ключом";
    }


    public Errors execute(String name, String coordinates, String impactSpeed, String realHero, String hasToothPick,
                          String weaponType, String mood, String carCool) {
        Errors error = validate(name, coordinates, impactSpeed);
        if (error == Errors.NOTHAVEERRORS) {
            CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(reader);
            Vehicle vehicle = creatorHumanBeingObject.create(name, coordinates, impactSpeed, realHero, hasToothPick,
                    weaponType, mood, carCool);
            boolean addStatus = addToDb("null", vehicle);
            if (!addStatus) error = Errors.UNKNOWNERROR;
        }
        return error;
    }

    private boolean addToDb(String userId, Vehicle vehicle) {
        Long id = null;
        if (userId.equals("null")) {
            Database db = Database.getInstance();
            ResultSet rs = db.getNewId("users_id_seq");
            try {
                rs.next();
                id = rs.getLong(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
        IDValidator idValidator = new IDValidator(userId);
        if (idValidator.isValid()) {
            id = Long.parseLong(userId);
        }
        if (id != null) {
            vehicle.setId(id);
            Database db = Database.getInstance();
            int update = db.addHumanBeingToDatabase("human_beings", vehicle);
            if (update > 0) {
                VehicleCollection.add(vehicle);
                System.out.println("Элемент успешно добавлен в коллекцию");
                return true;
            }
        }
        return false;

    }

    private Errors validate(String name, String coordinates, String impactSpeed) {
        List<Validator> validators = new ArrayList<>();
        validators.add(new NameValidator(name));
        validators.add(new CoordinatesValidator(coordinates.split(",")));
        validators.add(new ImpactSpeedValidator(impactSpeed));
        Errors error = Errors.NOTHAVEERRORS;
        for (Validator validator : validators) {
            error = validator.validateAll();
            if (error != Errors.NOTHAVEERRORS) return error;
        }
        return error;

    }

}
