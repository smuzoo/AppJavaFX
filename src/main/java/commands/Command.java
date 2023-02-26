package commands;

import collection.HumanBeing;

import java.util.Map;
import java.util.UUID;

public interface Command {
    void execute(Map<UUID, HumanBeing> humanBeingCollection);
}
