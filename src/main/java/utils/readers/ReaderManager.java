package utils.readers;

public class ReaderManager extends Reader{

    private Reader reader;
    private NameReader nameReader;

    public ReaderManager(Reader reader, NameReader nameReader) {
        this.reader = reader;
        this.nameReader = nameReader;
    }

    public String getNewLine(){
        String request = reader.getNewLine();
        if(request == null){
            setReader(new ReaderFromConsole());
            setNameReader(NameReader.READERCONSOLE);
            request = reader.getNewLine();
        }
        return request;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public NameReader getNameReader() {
        return nameReader;
    }

    public void setNameReader(NameReader nameReader) {
        this.nameReader = nameReader;
    }
}
