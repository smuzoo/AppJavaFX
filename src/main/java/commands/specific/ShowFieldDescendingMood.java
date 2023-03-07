package commands.specific;

import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;

import java.util.*;

public class ShowFieldDescendingMood implements Command {

    @Override
    public void execute(String ignore){
        List<HumanBeing> humanBeingSortedByMood = new ArrayList<>(HumanBeingCollection.getHumanBeings());
        humanBeingSortedByMood.sort((human1, human2) -> {
            if (human1.getMood() == null & human2.getMood() != null) return 1;
            else if (human1.getMood() != null & human2.getMood() == null) return -1;
            else if (human1.getMood() == null & human2.getMood() == null) return 0;
            return human2.getMoodPower() - human1.getMoodPower();
        });
        for(HumanBeing human : humanBeingSortedByMood){
            if(human.getMood() == null) System.out.println("null");
            else System.out.println(human.getMood());
        }
    }

    @Override
    public String description(){
        return "print_field_descending_mood : вывести значения поля mood всех элементов в порядке убывания";
    }
}
