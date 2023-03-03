package commands;

public class RemoveElement implements Command{

    @Override
    public void execute(){

    }

    @Override
    public String description(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
