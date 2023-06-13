package Database;

import collection.Vehicle;

import java.sql.*;

/**
 * The type Database.
 */
public class Database {
    /**
     * The Config.
     */
    DatabaseConfig config = new DatabaseConfig("localhost");
    /**
     * The Url.
     */
    String URL = config.getDatabaseUrl();
    /**
     * The User.
     */
    String USER = config.getDatabaseUsername();
    /**
     * The Password.
     */
    String PASSWORD = config.getDatabasePassword();

    // Переменная для хранения единственного экземпляра объекта Database
    private static Database instance = null;

    // Переменная для хранения соединения с базой данных
    private Connection connection;

    // Конструктор делается приватным, чтобы запретить создание объектов извне
    private Database() {
        try {
            // Подключаемся к базе данных
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
// Метод для получения единственного экземпляра объекта Database
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    /**
     * Is exist in db boolean.
     *
     * @param table      the table
     * @param field      the field
     * @param valueField the value field
     * @return the boolean
     */
    public boolean isExistInDB(String table, String field, String valueField){
        String sqlRequest = "SELECT * FROM " + table + " WHERE " + field + " = ?";
        ResultSet rs = executePrepareStatement(sqlRequest, valueField);
        if(rs == null) return false;
        try {
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса: " + e.getMessage());
            return false;
        }
    }

    /**
     * Add user to db.
     *
     * @param table the table
     * @param login the login
     * @param salt  the salt
     * @param hash  the hash
     */
    public void addUserToDB(String table, String login, String salt, String hash){
        String sqlRequest = "INSERT INTO " + table + " (login, salt, hash) VALUES (?, ?, ?)";
        executePrepareStatement(sqlRequest, login, salt, hash);
    }

    /**
     * Get field by field string.
     *
     * @param table         the table
     * @param setField      the set field
     * @param valueSetField the value set field
     * @param getField      the get field
     * @return the string
     */
    public String getFieldByField(String table, String setField, String valueSetField, String getField){
        String sqlRequest = "SELECT " + getField + " FROM " + table + " WHERE " + setField + " = ?";
        ResultSet rs = executePrepareStatement(sqlRequest, valueSetField);
        try {
            rs.next();
            return rs.getString(getField);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get human beings result set.
     *
     * @return the result set
     */
    public ResultSet getVehicles(){
        String sqlRequest = "SELECT * FROM human_beings";
        return executePrepareStatement(sqlRequest);
    }

    /**
     * Get new id result set.
     *
     * @param table the table
     * @return the result set
     */
    public ResultSet getNewId(String table){
        String sqlRequest = "SELECT nextval (?)";
        return executePrepareStatement(sqlRequest, table);
    }

    /**
     * Add human being to database int.
     *
     * @param table the table
     * @param human the human
     * @return the int
     */
    public int addHumanBeingToDatabase(String table, Vehicle human){
        return addNewHumanBeing(table, human.getId(), human.getName(), human.getCoordinates().getX(),
                human.getCoordinates().getY(), human.getCreationDate(), human.isRealHero(), human.isHasToothpick(),
                human.getImpactSpeed(), human.getStringWeaponType(), human.getFuelTypeString(),
                human.getCar().getStatus(), human.getUserLogin());
    }

    private int addNewHumanBeing(String table, Long id, String name, float x, Integer y, Timestamp localDate,
                                boolean realHero, boolean hasToothpick, Integer impactSpeed, String weaponType,
                                String mood, boolean carCool, String userLogin){
        String sqlRequest = "INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement psmt = connection.prepareStatement(sqlRequest)) {
        psmt.setLong(1, id);
        psmt.setString(2, name);
        psmt.setFloat(3, x);
        psmt.setInt(4, y);
        psmt.setTimestamp(5, localDate);
        psmt.setBoolean(6, realHero);
        psmt.setBoolean(7, hasToothpick);
        psmt.setInt(8, impactSpeed);
        psmt.setString(9, weaponType);
        psmt.setString(10, mood);
        psmt.setBoolean(11, carCool);
        psmt.setString(12, userLogin);
        return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Execute prepare statement result set.
     *
     * @param sqlRequest the sql request
     * @param values     the values
     * @return the result set
     */
    public ResultSet executePrepareStatement(String sqlRequest, String... values){
        try {
            PreparedStatement psmt = connection.prepareStatement(sqlRequest);
            for(int i = 0; i < values.length; i++){
                psmt.setString(i+1, values[i]);
            }
            return psmt.executeQuery();
        }catch (SQLException e) {
           return null;
        }
    }

    /**
     * Truncate table int.
     *
     * @param table the table
     * @return the int
     */
    private int truncateTable(String table){
        String sqlRequest = "TRUNCATE TABLE " + table;
        try{
            Statement smt = connection.createStatement();
            return smt.executeUpdate(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int truncateTable(String table1, String table2){
       return truncateTable(table1) + alterSequence(table2);
    }

    private int alterSequence(String table){
        String sqlRequest = "ALTER SEQUENCE users_id_seq RESTART WITH 1";
        try{
            Statement smt = connection.createStatement();
            return smt.executeUpdate(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    /**
     * Delete by id int.
     *
     * @param table the table
     * @param id    the id
     * @return the int
     */
    public int deleteById(String table, Long id){
        String sqlRequest = "DELETE FROM " + table + " WHERE id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sqlRequest)) {
            psmt.setLong(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Ошибка закрытия соединения с базой данных: " + e.getMessage());
        }
    }

}

