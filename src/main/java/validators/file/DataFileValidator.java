package validators.file;

import validators.IsValidating;

import java.util.ArrayList;
import java.util.List;

public class DataFileValidator {
    private final String[] data;
    private List<IsValidating> validatesMethods = new ArrayList<>();
    public DataFileValidator(String[] data){
        this.data = data;
    }

    private boolean isNotNull(){
        /* Check id, name, coordinates, creationDate, realHero, hasToothpick on null */
        for(int i =0; i < 6; i++){
            if(data[i] == null) return true;
        }
        return false;
    }

    private boolean isBiggerX(){
        return Float.parseFloat(data[2]) > -809f;
    }

    private boolean isBiggerImpactSpeed(){
        return Integer.parseInt(data[7]) > 496;
    }

    private void addValidMethods(){
        validatesMethods.add(this::isNotNull);
        validatesMethods.add(this::isBiggerX);
        validatesMethods.add(this::isBiggerImpactSpeed);
    }

    public boolean isValidateData(){
        addValidMethods();
        for(IsValidating method : validatesMethods){
            if(method.isNotValidate()) return false;
        }
        return true;
    }

}
