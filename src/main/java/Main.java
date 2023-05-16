import Database.Database;
import application.Application;
import collection.HumanBeing;
import collection.HumanBeingCollection;

/**
 * The main class to launch app.
 *
 * @author Kozhinov Pavel and Dudar Ilia
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Database db = Database.getInstance();
        HumanBeingCollection.readFromDatabase();
        Application.open();
        db.closeConnection();
    }
}
