package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.ImpactSpeedValidator;

public class ShowLessThanImpactSpeed implements Command {

    @Override
    public void execute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(argument);
            for(HumanBeing human : HumanBeingCollection.getHumanBeings()){
                if(human.getImpactSpeed() < impactSpeed) System.out.println(human);
            }
        }
    }

    @Override
    public String description(){
        return "filter_less_than_impact_speed impactSpeed : вывести элементы, " +
                "значение поля impactSpeed которых меньше заданного";
    }
}
