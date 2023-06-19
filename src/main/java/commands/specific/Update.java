package commands.specific;

import Database.Database;
import collection.Fields;
import collection.Vehicle;
import collection.VehicleCollection;
import validators.Errors;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Update {

    private final Vehicle human;

    public Update(Vehicle human) {
        this.human = human;
    }

    public Vehicle getHuman() {
        return human;
    }

    public Errors updateVehicle(int idField, String valueField){
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        Errors error = Errors.NOTHAVEERRORS;
        if(idField == 0) {
            error = new NameValidator(valueField).validateAll();
        } else if (idField == 1) {
            error = new CoordinatesValidator(valueField.split(",")).validateAll();
        } else if (idField == 2) {
            error = new ImpactSpeedValidator(valueField).validateAll();
        }
        Fields field = Fields.getForOrder(idField+1);
        if(error == Errors.NOTHAVEERRORS) {
            Predicate<String> notNullSetter = notNullSetters.get(field);
            if(notNullSetter == null) updateNullSetter(human, field, valueField);
            else notNullSetter.test(valueField);

        }
    return error;
    }

    public Errors updateX(String valueField) {
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        Errors error = Errors.NOTHAVEERRORS;
        error = new CoordinatesValidator(new String[]{valueField, String.valueOf(human.getY())}).validateAll();
        Fields field = Fields.getForOrder(2);
        if (error == Errors.NOTHAVEERRORS) {
            Predicate<String> notNullSetter = notNullSetters.get(field);
            notNullSetter.test(valueField + "," + human.getY());
        }
        return error;
    }

    public Errors updateY(String valueField) {
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        Errors error = new CoordinatesValidator(new String[]{String.valueOf(human.getX()), valueField}).validateAll();
        Fields field = Fields.getForOrder(2);
        if (error == Errors.NOTHAVEERRORS) {
            Predicate<String> notNullSetter = notNullSetters.get(field);
            notNullSetter.test(human.getX() + "," + valueField);
        }
        return error;
    }

    public void updateCollection(){
        Database db = Database.getInstance();
        db.deleteById("human_beings", human.getId());
        int update = db.addHumanBeingToDatabase("human_beings", human);
        if (update > 0) {
            VehicleCollection.add(human);
            System.out.println(human.getFuelType());
            System.out.println("поле было успешно изменено");
        }
    }

    public void updateNullSetter(Vehicle human, Fields field, String valueField){
        Map<Fields, Consumer<String>> setters = human.getSetters();
        setters.get(field).accept(valueField);
    }
}
