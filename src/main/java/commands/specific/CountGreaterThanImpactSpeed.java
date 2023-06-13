package commands.specific;

import collection.VehicleCollection;
import commands.Command;
import validators.Errors;
import validators.commands.ImpactSpeedValidator;

/**
 * The command Count greater than impact speed.
 */
public class CountGreaterThanImpactSpeed implements Command {

    private String result;

    public String getResult() {
        return result;
    }

    @Override
    public void execute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(argument);
            long count = VehicleCollection.getVehicles().stream().filter(human ->
                    human.getImpactSpeed() > impactSpeed).count();
            System.out.println("Количество элементов HumanBeing, превышающих impactSpeed=" + impactSpeed +
                    " равен " + count);
        }
    }

    public Errors count(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        Errors error = impactSpeedValidator.validateAll();
        if(error == Errors.NOTHAVEERRORS){
            Integer impactSpeed = Integer.parseInt(argument);
            long count = VehicleCollection.getVehicles().stream().filter(human ->
                    human.getImpactSpeed() > impactSpeed).count();
           result = String.valueOf(count);

        }
        return error;
    }




    @Override
    public String description(){
        return "count_greater_than_impact_speed" + " impactSpeed" + " : вывести количество элементов, значение поля impactSpeed которых больше заданного";
    }

}
