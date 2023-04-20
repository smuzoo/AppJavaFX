package collection;

/**
 * The type Reader from db.
 */
public class ReaderFromDB implements Runnable{
    @Override
    public void run(){
        HumanBeingCollection.readFromDatabase();
    }

}
