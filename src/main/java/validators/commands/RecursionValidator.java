package validators.commands;

import validators.Errors;
import validators.Validator;

/**
 * The type Recursion validator.
 */
public class RecursionValidator extends Validator {

    private final int countRecursion;
    private final int MAX_RECURSION;

    /**
     * Instantiates a new Recursion validator.
     *
     * @param countRecursion the count recursion
     * @param MAX_RECURSION  the max count recursion
     */
    public RecursionValidator(int countRecursion, int MAX_RECURSION) {
        this.countRecursion = countRecursion;
        this.MAX_RECURSION = MAX_RECURSION;
    }

    private boolean isManyRecursion(){
        return countRecursion > MAX_RECURSION;
    }

    @Override
    public void addAllErrors(){
        addError(this::isManyRecursion, Errors.MANYRECURSIONS);
    }
}
