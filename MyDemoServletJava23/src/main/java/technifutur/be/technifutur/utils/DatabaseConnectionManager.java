package technifutur.be.technifutur.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static String CONNECTION_STRING;
    private static String DB_USER;
    private static String DB_PASSWORD;
    public void initDatabaseContext(String connectionString,String dbUser,String dbPassword){
        CONNECTION_STRING = connectionString;
        DB_USER = dbUser;
        DB_PASSWORD = dbPassword;
    }

    private static Connection connection;
    public static Connection getConnected() throws SQLException{
        if(connection == null || connection.isClosed()){
           connection= DriverManager.getConnection(CONNECTION_STRING,DB_USER,DB_PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if (connection != null && !connection.isClosed()){
                connection.close();
        }
    }
}
