import Database.Database;
import application.Application;
import collection.HumanBeingCollection;

/**
 * The main class to launch app.
 *
 * @author Kozhinov Pavel and Dudar Ilia
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        /*
        Database db = Database.getInstance();
        try{
            Thread readerDB = new Thread(new ReaderFromDB());
            readerDB.start();
            Authentication.setReader(new ReaderFromConsole());
            Authentication.auth();
            ReaderFromConsole reader = new ReaderFromConsole();
            CommandController commandController = new CommandController(reader);
            String request;
            while(!((request = reader.getNewLine()).equals("exit"))){
                commandController.executeCommand(request);
            }
        }
        finally {
            db.closeConnection();
        }
*/
        Database db = Database.getInstance();
        HumanBeingCollection.readFromDatabase();
        Application.open();
        db.closeConnection();
    }
}
