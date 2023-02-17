package validators;

import java.util.*;

public class ValidatorNameFile extends ValidatorWithExit{
    private String FILE_PATH;
    public ValidatorNameFile(String env){
        this.FILE_PATH = env;
    }
    private boolean validateOnNull() {
        return FILE_PATH == null;
    }

    private boolean validateOnEmpty() {
        return FILE_PATH.equals("");
    }
    @Override
    protected void addValidError(){
        validatesMethods.put(this::validateOnEmpty, EnvErrors.EMPTYENVIRONMENT);
        validatesMethods.put(this::validateOnNull, EnvErrors.WRONGENVIRONMENT);

    }


}
