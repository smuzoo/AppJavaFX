package validators.commands;

import validators.Errors;
import validators.Validator;

public class RecursionValidator extends Validator {

    private final int countRecursion;
    private final int MAX_RECURSION;

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
