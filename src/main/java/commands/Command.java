package commands;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command.
     *
     * @param argument the argument
     */
    void execute(String argument);

    /**
     * Description command.
     *
     * @return the string
     */
    String description();
}
