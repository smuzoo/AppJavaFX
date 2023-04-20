import Database.Database;
import authentication.Authentication;
import collection.HumanBeingCollection;
import collection.ReaderFromDB;
import commands.CommandController;
import utils.readers.ReaderFromConsole;

/**
 * The main class to launch app.
 *
 * @author Kozhinov Pavel and Dudar Ilia
 * @author Dudar Ilia
 * @version 1.0
 */
public class Main {
    /**
     * The main method.
     *
     * @param args the input file collection
     */
    public static void main(String[] args) {
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

    }
}
