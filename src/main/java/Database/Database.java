package Database;

import collection.Car;
import collection.HumanBeing;
import collection.Mood;
import collection.WeaponType;

import java.sql.*;
import java.time.LocalDate;

public class Database {
    // Константы для подключения к базе данных
    DatabaseConfig config = new DatabaseConfig("localhost");
    String URL = config.getDatabaseUrl();
    String USER = config.getDatabaseUsername();
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

    // Метод для получения единственного экземпляра объекта Database
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Метод для выполнения запросов к базе данных
    public void executeQuery(String query) {
        try {
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }



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

    public void addUserToDB(String table, String login, String salt, String hash){
        String sqlRequest = "INSERT INTO " + table + " (login, salt, hash) VALUES (?, ?, ?)";
        executePrepareStatement(sqlRequest, login, salt, hash);
    }

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

    public ResultSet getHumanBeings(){
        String sqlRequest = "SELECT * FROM human_beings";
        return executePrepareStatement(sqlRequest);
    }

    public ResultSet getNewId(String table){
        String sqlRequest = "SELECT nextval (?)";
        return executePrepareStatement(sqlRequest, table);
    }

    public int addHumanBeingToDatabase(String table, HumanBeing human){
        return addNewHumanBeing(table, human.getId(), human.getName(), human.getCoordinates().getX(),
                human.getCoordinates().getY(), human.getCreationDate(), human.isRealHero(), human.isHasToothpick(),
                human.getImpactSpeed(), human.getWeaponType().toString(), human.getMood().toString(),
                human.getCar().getStatus(), human.getUserLogin(), human.getPower());
    }

    private int addNewHumanBeing(String table, Long id, String name, float x, Integer y, Timestamp localDate,
                                boolean realHero, boolean hasToothpick, Integer impactSpeed, String weaponType,
                                String mood, boolean carCool, String userLogin, long power){
        String sqlRequest = "INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        psmt.setLong(13, power);
        return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

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

    public int truncateTable(String table){
        String sqlRequest = "TRUNCATE TABLE " + table;
        try(Statement smt = connection.createStatement()){
            return smt.executeUpdate(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(String table, Long id){
        String sqlRequest = "DROP TABLE FROM " + table + " WHERE id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sqlRequest)) {
            psmt.setLong(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для закрытия соединения с базой данных
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Ошибка закрытия соединения с базой данных: " + e.getMessage());
        }
    }

}

