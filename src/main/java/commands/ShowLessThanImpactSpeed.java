package commands;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import validators.commands.ImpactSpeedValidator;

public class ShowLessThanImpactSpeed implements Command{

    @Override
    public void execute(String[] arguments){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(arguments);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(arguments[1]);
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
