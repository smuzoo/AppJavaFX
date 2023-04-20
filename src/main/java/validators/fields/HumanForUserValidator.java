package validators.fields;

import authentication.User;
import collection.HumanBeing;
import validators.Errors;
import validators.Validator;

/**
 * The type Human for user validator.
 */
public class HumanForUserValidator extends Validator {

    private HumanBeing human;

    /**
     * Instantiates a new Human for user validator.
     *
     * @param human the human
     */
    public HumanForUserValidator(HumanBeing human) {
        this.human = human;
    }

    private boolean NotCreateThisUser(){
        return !human.getUserLogin().equals(User.getLogin());
    }

    protected void addAllErrors(){
        addError(this::NotCreateThisUser, Errors.NOTCREATETHISUSER);
    }
}
