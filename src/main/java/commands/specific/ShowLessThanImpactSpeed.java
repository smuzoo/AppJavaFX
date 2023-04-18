package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import validators.commands.ImpactSpeedValidator;
import static colors.Colors.*;

/**
 * The command Show less than impact speed.
 */
public class ShowLessThanImpactSpeed implements Command {

    @Override
    public void execute(String argument){
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(argument);
        if(impactSpeedValidator.isValid()){
            Integer impactSpeed = Integer.parseInt(argument);
            HumanBeingCollection.getHumanBeings().stream().filter(human ->
                    human.getImpactSpeed() < impactSpeed).forEach(System.out::println);
        }
    }

    @Override
    public String description(){
        return BLUE + "filter_less_than_impact_speed" + PURPLE + " impactSpeed" + RESET + " : вывести элементы, " +
                "значение поля impactSpeed которых меньше заданного";
    }
}
