package utils.readers;

/**
 * The type Reader manager. Controls switching between classes ReaderFromConsole and ReaderFromFile
 */
public class ReaderManager extends Reader{

    private Reader reader;
    private NameReader nameReader;

    /**
     * Instantiates a new Reader manager.
     *
     * @param reader     the reader
     * @param nameReader the name reader
     */
    public ReaderManager(Reader reader, NameReader nameReader) {
        this.reader = reader;
        this.nameReader = nameReader;
    }

    /**
     * Gets a new line.
     * if a new line == null. Switch reader to ReaderFromConsole.
     * @return request the new line
     */
    public String getNewLine(){
        String request = reader.getNewLine();
        if(request == null){
            setReader(new ReaderFromConsole());
            setNameReader(NameReader.READERCONSOLE);
            request = reader.getNewLine();
        }
        return request;
    }

    /**
     * Gets reader.
     *
     * @return the reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Sets reader.
     *
     * @param reader the reader
     */
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    /**
     * Gets name reader.
     *
     * @return the name reader
     */
    public NameReader getNameReader() {
        return nameReader;
    }

    /**
     * Sets name reader.
     *
     * @param nameReader the name reader
     */
    public void setNameReader(NameReader nameReader) {
        this.nameReader = nameReader;
    }
}
