import Database.Database;
import collection.HumanBeing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HumanBeingGeneratorLauncher {

    private final int COUNT_NEW_HUMAN_BEING_OBJECTS = 100;
    @Test
    public void testGenerateRandomHumanBeing() {
        HumanBeing humanBeing = HumanBeingGenerator.generateRandomHumanBeing();
        System.out.println(humanBeing);
        assertNotNull(humanBeing);
        // Add more assertions to test the generated HumanBeing object
    }

    @Test
    public void testGenerateRandomHumanBeingInDb(){
        for(int i = 0; i < COUNT_NEW_HUMAN_BEING_OBJECTS; i++){
            HumanBeing humanBeing = HumanBeingGenerator.generateRandomHumanBeing();
            Database.getInstance().addHumanBeingToDatabase("human_beings", humanBeing);
        }
    }

}
