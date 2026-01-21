package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clasa ce realizeaza conexiunea cu baza de date.
 * @author oana
 */

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/monitorizare_veterinara";
    private static final String USER = "root";
    private static final String PASS = "parola";
    
    /**
     * @return Driver inregistrat ce se potriveste cu URL-ul conexiunii la baza de date.
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
