package session17.bai2;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private static final String Driver= "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String User = "postgres";
    private static final String Password = "23122004";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, User, Password);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi kết nối CSDL", e);
        }
    }
}