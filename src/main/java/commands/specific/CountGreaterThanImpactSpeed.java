package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.ImpactSpeedValidator;

import static colors.Colors.*;

public class CountGreaterThanImpactSpeed implements Command {

    @Override
    public void execute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(argument);
            int count = 0;
            for(HumanBeing human : HumanBeingCollection.getHumanBeings()){
                if(human.getImpactSpeed() > impactSpeed) count++;
            }
            System.out.println("Количество элементов HumanBeing, превышающих impactSpeed=" + impactSpeed +
                    " равен " + count);
        }
    }

    @Override
    public String description(){
        return BLUE + "count_greater_than_impact_speed" + PURPLE + " impactSpeed" + RESET + " : вывести количество элементов, значение поля impactSpeed которых больше заданного";
    }

}
