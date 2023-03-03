package commands;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import validators.commands.ImpactSpeedValidator;

public class CountGreaterThanImpactSpeed implements Command{

    @Override
    public void execute(String[] arguments){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(arguments);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(arguments[1]);
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
        return "count_greater_than_impact_speed impactSpeed : вывести количество элементов, значение поля impactSpeed которых больше заданного";
    }

}
