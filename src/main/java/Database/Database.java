package Database;

import java.sql.*;

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
        String sqlRequest = "SELECT * FROM ? WHERE ? = ?";
        ResultSet rs = executePrepareStatement(sqlRequest, table, field, valueField);
        if(rs == null) return false;
        try {
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса: " + e.getMessage());
            return false;
        }
    }

    public void addUserToDB(String table, String login, String salt, String hash){
        String sqlRequest = "INSERT INTO ? (login, salt, hash) VALUES (?, ?, ?)";
        executePrepareStatement(sqlRequest, table, login, salt, hash);
    }

    public String getFieldByField(String table, String setField, String valueSetField, String getField){
        String sqlRequest = "SELECT ? FROM ? WHERE ? = ?";
        ResultSet rs = executePrepareStatement(sqlRequest, table, setField, valueSetField, getField);
        try {
            return rs.getString("salt");
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
            System.err.println("Ошибка выполнения запроса: " + e.getMessage());
            return null;
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

