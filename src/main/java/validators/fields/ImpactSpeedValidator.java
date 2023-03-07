package validators.fields;

import validators.Errors;
import validators.Validator;

public class ImpactSpeedValidator extends Validator {

    final private String impactSpeed;

    public ImpactSpeedValidator(String impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    private boolean isNotCanTransformImpactSpeed(){
        try{
            Integer.parseInt(impactSpeed);
        }catch (NumberFormatException e){
            return true;
        }
        return false;
    }

    private boolean isBiggerImpactSpeed(){
        return Integer.parseInt(impactSpeed) > 496;
    }

    @Override
    public void addAllErrors(){
        addError(this::isNotCanTransformImpactSpeed, Errors.NOTCANTRANSFORMTOINT);
        addError(this::isBiggerImpactSpeed, Errors.BIGGERIMPACTSPEED);
    }


}
