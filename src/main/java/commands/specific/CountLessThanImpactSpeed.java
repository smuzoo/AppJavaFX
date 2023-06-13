package commands.specific;

import collection.VehicleCollection;
import commands.Command;
import validators.Errors;
import validators.commands.ImpactSpeedValidator;

/**
 * The command Show less than impact speed.
 */
public class CountLessThanImpactSpeed implements Command {

    private String result;

    public String getResult() {
        return result;
    }

    @Override
    public void execute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(argument);
            VehicleCollection.getVehicles().stream().filter(human ->
                    human.getImpactSpeed() < impactSpeed).forEach(System.out::println);
        }
    }

    public Errors isExecute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        Errors error = impactSpeedValidator.validateAll();
        if(error == Errors.NOTHAVEERRORS){
            Integer impactSpeed = Integer.parseInt(argument);
            long count = VehicleCollection.getVehicles().stream().filter(human ->
                    human.getImpactSpeed() < impactSpeed).count();
            result = String.valueOf(count);
        }
        return error;
    }

    @Override
    public String description(){
        return "filter_less_than_impact_speed" + " impactSpeed" + " : вывести элементы, " +
                "значение поля impactSpeed которых меньше заданного";
    }
}
