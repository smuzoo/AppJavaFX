package validators.fields;

import authentication.User;
import collection.Vehicle;
import validators.Errors;
import validators.Validator;

/**
 * The type Human for user validator.
 */
public class HumanForUserValidator extends Validator {

    private Vehicle human;

    /**
     * Instantiates a new Human for user validator.
     *
     * @param human the human
     */
    public HumanForUserValidator(Vehicle human) {
        this.human = human;
    }

    private boolean NotCreateThisUser(){
        return !human.getUserLogin().equals(User.getLogin());
    }

    protected void addAllErrors(){
        addError(this::NotCreateThisUser, Errors.NOTCREATETHISUSER);
    }
}
