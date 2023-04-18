package validators.fields;

import authentication.User;
import collection.HumanBeing;
import validators.Errors;
import validators.Validator;

public class HumanForUserValidator extends Validator {

    private HumanBeing human;

    public HumanForUserValidator(HumanBeing human) {
        this.human = human;
    }

    private boolean NotCreateThisUser(){
        return human.getUserLogin().equals(User.getLogin());
    }

    protected void addAllErrors(){
        addError(this::NotCreateThisUser, Errors.NOTCREATETHISUSER);
    }
}
