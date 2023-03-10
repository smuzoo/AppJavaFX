package validators.file;

import validators.IsValidating;

import java.util.ArrayList;
import java.util.List;

public class DataFileValidator {
    private final int COLUMNS_IN_FILE = 11;
    private final String[] data;
    private final List<IsValidating> validatesMethods = new ArrayList<>();
    public DataFileValidator(String[] data){
        this.data = data;
    }

    private boolean isNotEnoughColumns(){
        return data.length != COLUMNS_IN_FILE;
    }

    private boolean isNull(){
        /* Check id, name, coordinates, creationDate, realHero, hasToothpick on null */
        for(int i =0; i < 6; i++){
            if(data[i] == null) return true;
        }
        return false;
    }

    private boolean isNotCanTransformToNumber(){
        try {
            Float.parseFloat(data[2]);
        }catch (NumberFormatException e){
            return true;
        }
        if(data[7].equals("")) return false;
        try {
            Integer.parseInt(data[7]);
        }catch (NumberFormatException e){
            return true;
        }
        return false;
    }

    private boolean isBiggerX(){
        return Float.parseFloat(data[2]) > -809f;
    }

    private boolean isBiggerImpactSpeed(){
        if(data[7].equals("")) return false;
        return Integer.parseInt(data[7]) > 496;
    }



    private void addValidMethods(){
        validatesMethods.add(this::isNotEnoughColumns);
        validatesMethods.add(this::isNull);
        validatesMethods.add(this::isNotCanTransformToNumber);
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
