package utils;

/**
 * The type File constant, store all constant about file.
 */
public class FileConstant {

    private static String FILE_PATH;

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public static String getFilePath() {
        return FILE_PATH;
    }
}
